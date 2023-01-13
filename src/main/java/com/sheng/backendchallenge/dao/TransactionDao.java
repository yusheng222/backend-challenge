package com.sheng.backendchallenge.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheng.backendchallenge.entity.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransactionDao extends BaseMapper<Transaction> {

    List<Transaction> findTransactionList(@Param("cus_id") Long customerId);
}
