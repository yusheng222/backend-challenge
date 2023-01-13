package com.sheng.backendchallenge.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sheng.backendchallenge.constant.PageConstant;
import com.sheng.backendchallenge.dao.TransactionDao;
import com.sheng.backendchallenge.entity.Transaction;
import com.sheng.backendchallenge.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionDao,Transaction> implements TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Override
    public List<Transaction> findTransactionList(Long customerId,Integer pageNo) {
        PageHelper.startPage(pageNo, PageConstant.PAGE_SIZE);
        List<Transaction> transactionList = transactionDao.findTransactionList(123456789123L);
        PageInfo<Transaction> page = new PageInfo<>(transactionList);
        return page.getList();
    }
}
