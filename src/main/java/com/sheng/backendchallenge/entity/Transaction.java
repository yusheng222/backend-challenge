package com.sheng.backendchallenge.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getAccId() {
        return accId;
    }

    public void setAccId(Long accId) {
        this.accId = accId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountIban() {
        return accountIban;
    }

    public void setAccountIban(String accountIban) {
        this.accountIban = accountIban;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCusId() {
        return cusId;
    }

    public void setCusId(Long cusId) {
        this.cusId = cusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(accId, that.accId) && Objects.equals(amount, that.amount) && Objects.equals(currency, that.currency) && Objects.equals(accountIban, that.accountIban) && Objects.equals(valueDate, that.valueDate) && Objects.equals(description, that.description) && Objects.equals(cusId, that.cusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, accId, amount, currency, accountIban, valueDate, description, cusId);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", accId=" + accId +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", accountIban='" + accountIban + '\'' +
                ", valueDate=" + valueDate +
                ", description='" + description + '\'' +
                ", cusId=" + cusId +
                '}';
    }
}
