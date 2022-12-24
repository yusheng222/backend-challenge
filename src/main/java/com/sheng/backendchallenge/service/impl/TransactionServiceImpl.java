package com.sheng.backendchallenge.service.impl;

import com.sheng.backendchallenge.constant.CommonStatusEnum;
import com.sheng.backendchallenge.dto.ResponseResult;
import com.sheng.backendchallenge.dto.TokenResult;
import com.sheng.backendchallenge.service.ConsumerService;
import com.sheng.backendchallenge.service.TransactionService;
import com.sheng.backendchallenge.utils.JwtUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Resource
    ConsumerService consumerService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public ResponseResult doTransactionProcess(String jwtToken, String date) {
        // set redis to check if request already exits
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(jwtToken + date, jwtToken + date);
        if (!flag){
            return ResponseResult.fail(CommonStatusEnum.MULTIPLE_REQUEST.getCode()
                    ,CommonStatusEnum.MULTIPLE_REQUEST.getValue());
        }
        // parse the JWT token passed from user
        TokenResult tokenResult = JwtUtils.checkToken(jwtToken);
        if (tokenResult == null) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),
                    CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String identifier = tokenResult.getIdentifier();

        // check if the accounts exits
        String[] split = date.trim().split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        long startTime = parseDate(year,month);
        long endTime = parseDate(year,month+1);



        return null;
    }


    private static long parseDate(int year, int month){


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DATE,1);
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTimeInMillis();
    }
}
