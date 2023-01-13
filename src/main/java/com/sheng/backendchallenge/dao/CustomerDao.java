package com.sheng.backendchallenge.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheng.backendchallenge.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerDao extends BaseMapper<Customer> {
    Long findCustomerByIdentifier(@Param("identifier") String identifier);
}
