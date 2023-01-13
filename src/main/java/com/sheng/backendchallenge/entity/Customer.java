package com.sheng.backendchallenge.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@TableName("customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long cusId;

    private String cusName;

    private String cusIdentifier;

    public Long getCusId() {
        return cusId;
    }

    public void setCusId(Long cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusIdentifier() {
        return cusIdentifier;
    }

    public void setCusIdentifier(String cusIdentifier) {
        this.cusIdentifier = cusIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(cusId, customer.cusId) && Objects.equals(cusName, customer.cusName) && Objects.equals(cusIdentifier, customer.cusIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cusId, cusName, cusIdentifier);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusId=" + cusId +
                ", cusName='" + cusName + '\'' +
                ", cusIdentifier='" + cusIdentifier + '\'' +
                '}';
    }
}
