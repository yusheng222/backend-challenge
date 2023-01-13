package com.sheng.backendchallenge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sheng.backendchallenge.entity.Transaction;

import java.util.List;

public interface TransactionService extends IService<Transaction> {

    List<Transaction> findTransactionList(Long customerId,Integer pageNo);
}
