package com.sheng.backendchallenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class RespTransaction {

    private String transactionId;

    private Long accId;

    private String currency;

    @JsonFormat(timezone = "GMT+8",pattern = "dd-MM-yyyy")
    private Date valueDate;

    private String description;

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    @Override
    public String toString() {
        return "RespTransaction{" +
                "transactionId='" + transactionId + '\'' +
                ", accId=" + accId +
                ", currency='" + currency + '\'' +
                ", valueDate=" + valueDate +
                ", description='" + description + '\'' +
                '}';
    }
}
