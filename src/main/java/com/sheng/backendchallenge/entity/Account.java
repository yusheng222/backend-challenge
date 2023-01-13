package com.sheng.backendchallenge.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;


@TableName("account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long accNo;

    private String cusId;

    private String currency;

    private String accIban;

    public Long getAccNo() {
        return accNo;
    }

    public void setAccNo(Long accNo) {
        this.accNo = accNo;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccIban() {
        return accIban;
    }

    public void setAccIban(String accIban) {
        this.accIban = accIban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accNo, account.accNo) && Objects.equals(cusId, account.cusId) && Objects.equals(currency, account.currency) && Objects.equals(accIban, account.accIban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accNo, cusId, currency, accIban);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accNo=" + accNo +
                ", cusId='" + cusId + '\'' +
                ", currency='" + currency + '\'' +
                ", accIban='" + accIban + '\'' +
                '}';
    }
}
