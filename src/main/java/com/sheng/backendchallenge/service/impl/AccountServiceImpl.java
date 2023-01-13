package com.sheng.backendchallenge.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sheng.backendchallenge.dao.AccountDao;
import com.sheng.backendchallenge.entity.Account;
import com.sheng.backendchallenge.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements AccountService {
    @Resource
    AccountDao accountDao;
    @Override
    public List<Long> findAccountsByCustomerId(Long customerId) {
        return accountDao.findAccountsByCustomerId(customerId);
    }
}
