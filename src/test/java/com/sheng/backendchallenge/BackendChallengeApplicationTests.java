package com.sheng.backendchallenge;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sheng.backendchallenge.dao.TransactionDao;
import com.sheng.backendchallenge.dto.ExchangeRateDto;
import com.sheng.backendchallenge.entity.Transaction;
import com.sheng.backendchallenge.service.AccountService;
import com.sheng.backendchallenge.service.CustomerService;
import com.sheng.backendchallenge.service.KafkaTransactionService;
import com.sheng.backendchallenge.service.TransactionService;
import com.sheng.backendchallenge.utils.ExchangeRateUtils;
import com.sheng.backendchallenge.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BackendChallengeApplicationTests {
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @Autowired
    KafkaTransactionService kafkaTransactionService;
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    TransactionService transactionService;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExchangeRateUtils exchangeRateUtils;
    @Test
    public void test() {
        Long customerByIdentifier = customerService.findCustomerByIdentifier("352227199602221315");
        System.out.println(customerByIdentifier);
        List<Long> accounts = accountService.findAccountsByCustomerId(customerByIdentifier);
        for (Long account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testTransactionSave() {
        Map<String,String> map = new HashMap<>();
        map.put("identifier","352227199602221315");
        String jwtToken = JwtUtils.generateToken(map);
        //String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZGVudGlmaWVyIjoiMzUyMjI3MTk5NjAyMjIxMzE1IiwiZXhwIjoxNjczNDI0ODE1fQ.BPFs8wdbQC-3bLB19QRgt8wsXke_YbCdMOTsgE_cNw4";
        String date = "2023-01";
        kafkaTransactionService.doTransactionProcess(jwtToken,date,Thread.currentThread().getName(),1);
    }
    //@Test
    //void contextLoads() {
    //}
    @Test
    public void getTransaction() {
        Transaction transaction = transactionDao.selectOne(new QueryWrapper<Transaction>().eq("transaction_id", "uniqueIdentifier0"));
        System.out.println(transaction.toString());
    }

    @Test
    public void testPageHelper(){
        //List<Transaction> transactionList = transactionService.findTransactionList(123456789123L);
        PageHelper.startPage(1,10);
        List<Transaction> transactionList = transactionDao.findTransactionList(123456789123L);
        PageInfo<Transaction> page = new PageInfo<>(transactionList);
        List<Transaction> list = page.getList();
        for (Transaction transaction : list) {
            System.out.println(transaction.toString());
        }
    }


    @Test
    public void testExchangeRate() {
        Map<String, ExchangeRateDto> map = exchangeRateUtils.requestExchangeRate();
        Iterator<Map.Entry<String, ExchangeRateDto>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            ExchangeRateDto value = iterator.next().getValue();
            System.out.println(value);
        }

    }

}
