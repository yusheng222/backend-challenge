package com.sheng.backendchallenge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sheng.backendchallenge.entity.Customer;

public interface CustomerService extends IService<Customer> {
    Long findCustomerByIdentifier(String identifier);
}
