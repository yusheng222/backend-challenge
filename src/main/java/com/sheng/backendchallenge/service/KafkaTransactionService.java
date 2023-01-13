package com.sheng.backendchallenge.service;


import com.sheng.backendchallenge.dto.ResponseResult;

public interface KafkaTransactionService {

    public ResponseResult doTransactionProcess(String jwtToken, String month, String groupId, Integer pageNo);
}
