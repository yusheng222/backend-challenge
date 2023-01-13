package com.sheng.backendchallenge.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sheng.backendchallenge.constant.CommonConstant;
import com.sheng.backendchallenge.constant.CommonStatusEnum;
import com.sheng.backendchallenge.dto.*;
import com.sheng.backendchallenge.entity.Transaction;
import com.sheng.backendchallenge.service.*;
import com.sheng.backendchallenge.utils.ExchangeRateUtils;
import com.sheng.backendchallenge.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class KafkaTransactionServiceImpl implements KafkaTransactionService {

    @Resource
    ConsumerService consumerService;

    @Resource
    CustomerService customerService;

    @Resource
    AccountService accountService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    RedisTemplate redisTemplate;

    @Autowired
    TransactionService transactionService;

    @Autowired
    ExchangeRateUtils exchangeRateUtils;

    @Override
    public ResponseResult doTransactionProcess(String jwtToken, String date, String groupId, Integer pageNo) {

        // parse the JWT token passed from user
        TokenResult tokenResult = JwtUtils.checkToken(jwtToken);
        if (tokenResult == null) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),
                    CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String identifier = tokenResult.getIdentifier();
        // find the accounts that belonged to the customer
        Long customerId = customerService.findCustomerByIdentifier(identifier);
        List<Long> accNums = accountService.findAccountsByCustomerId(customerId);

        // check if the accounts exits
        String[] split = date.trim().split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        long startTime = parseDate(year, month);
        long endTime = parseDate(year, month + 1);

        // process the kafka message, find out all the relative data and save them into database
        // check if the result has been searched and saved, in case true, skip search and save steps
        Boolean canSearch = stringRedisTemplate.opsForValue().setIfAbsent(CommonConstant.PREFIX_KAFKA + customerId + "-" + date, customerId + date);
        if (canSearch) {
            try {
                consumerService.consumerByTime(groupId, startTime, endTime, accNums, customerId);
            } catch (Exception e) {
                e.printStackTrace();
                stringRedisTemplate.delete(CommonConstant.PREFIX_KAFKA + customerId + "-" + date);
            }
        }
        // return page list of transaction
        List<Transaction> transactionList = transactionService.findTransactionList(customerId, pageNo);

        // request third party to get current exchangeRate
        // the third party api has limited QPS, so that saving the exchange rate map into redis, update every minute
        Map<String, ExchangeRateDto> exchangeRateMap = redisTemplate.opsForHash().entries("exchangeRateMap");
        if (exchangeRateMap.size() == 0) {
            exchangeRateMap = exchangeRateUtils.requestExchangeRate();
            redisTemplate.opsForHash().putAll("exchangeRateMap",exchangeRateMap);
            redisTemplate.expire(exchangeRateMap,60,TimeUnit.SECONDS);
        }

        BigDecimal totalCredit = new BigDecimal(0);
        BigDecimal totalDebit = new BigDecimal(0);
        List<RespTransaction> respTransactionList = new ArrayList<>();
        // process the paginated list
        for (Transaction transaction : transactionList) {
            RespTransaction respTransaction = new RespTransaction();

            BigDecimal amount = transaction.getAmount();
            String currency = transaction.getCurrency();
            Double exchangeRate = 0.0;
            if ("CNY".equals(currency)) {
                exchangeRate = 1.0;
            } else if ("JPY".equals(currency)) {
                // here 100 is because the form of third party api return the exchange rate for JPY/CNY in 100JPY/CNY
                exchangeRate = (exchangeRateMap.get("100" + currency + "/CNY").getPrice()) / 100;
            } else {
                try {
                    exchangeRate = exchangeRateMap.get(currency + "/CNY").getPrice();
                }catch (Exception e) {
                    System.out.println(exchangeRateMap);
                    System.out.println(currency);
                }

            }

            BigDecimal exchangedBaseMoney = amount.abs().multiply(BigDecimal.valueOf(exchangeRate));
            if (amount.doubleValue() < 0) {
                totalCredit = totalCredit.add(exchangedBaseMoney);
            } else if (amount.doubleValue() > 0) {
                totalDebit = totalDebit.add(exchangedBaseMoney);
            }
            respTransaction.setTransactionId(transaction.getTransactionId());
            respTransaction.setAmount(amount);
            respTransaction.setCurrency(currency);
            respTransaction.setAccId(transaction.getAccId());
            respTransaction.setValueDate(transaction.getValueDate());
            respTransaction.setDescription(transaction.getDescription());
            respTransactionList.add(respTransaction);
        }
        TransactionRespDto response = new TransactionRespDto();
        response.setCusId(customerId);
        response.setTotalCredit(totalCredit.setScale(2, BigDecimal.ROUND_HALF_UP));
        response.setTotalDebit(totalDebit.setScale(2, BigDecimal.ROUND_HALF_UP));
        response.setTransactionList(respTransactionList);
        return ResponseResult.success(response);
    }


    private static long parseDate(int year, int month) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}
