package com.sheng.backendchallenge.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("transaction")
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private String transactionId;

    private Long accId;

    private BigDecimal amount;

    private String currency;

    private String accountIban;

    private Date valueDate;

    private String description;

    private Long cusId;
}
