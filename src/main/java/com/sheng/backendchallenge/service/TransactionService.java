package com.sheng.backendchallenge.service;


import com.sheng.backendchallenge.dto.ResponseResult;

public interface TransactionService {

    public ResponseResult doTransactionProcess(String jwtToken, String month);
}
