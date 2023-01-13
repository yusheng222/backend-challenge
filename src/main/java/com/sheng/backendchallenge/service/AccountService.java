package com.sheng.backendchallenge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sheng.backendchallenge.entity.Account;

import java.util.List;

public interface AccountService extends IService<Account> {
    List<Long> findAccountsByCustomerId(Long customerId);
}
