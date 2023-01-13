package com.sheng.backendchallenge.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long cusId;

    private String cusName;

    private String cusIdentifier;

}
