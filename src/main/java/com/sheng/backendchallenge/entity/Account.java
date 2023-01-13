package com.sheng.backendchallenge.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long accNo;

    private String cusId;

    private String currency;

    private String accIban;

}
