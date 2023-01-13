package com.sheng.backendchallenge.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sheng.backendchallenge.dao.CustomerDao;
import com.sheng.backendchallenge.entity.Customer;
import com.sheng.backendchallenge.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, Customer>implements CustomerService {
    @Resource
    private CustomerDao customerDao;

    @Override
    public Long findCustomerByIdentifier(String identifier) {
        return customerDao.findCustomerByIdentifier(identifier);
    }

}
