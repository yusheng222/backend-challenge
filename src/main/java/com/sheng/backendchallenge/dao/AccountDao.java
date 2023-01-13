package com.sheng.backendchallenge.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheng.backendchallenge.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountDao extends BaseMapper<Account> {

    List<Long> findAccountsByCustomerId(@Param("cus_id") Long customerId);
}
