package com.sheng.backendchallenge.dto;

import com.sheng.backendchallenge.entity.Transaction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

public class TransactionRespDto {

    private Long cusId;

    private BigDecimal totalDebit;

    private BigDecimal totalCredit;

    private List<RespTransaction> transactionList;

    public Long getCusId() {
        return cusId;
    }

    public void setCusId(Long cusId) {
        this.cusId = cusId;
    }

    public BigDecimal getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(BigDecimal totalDebit) {
        this.totalDebit = totalDebit;
    }

    public BigDecimal getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(BigDecimal totalCredit) {
        this.totalCredit = totalCredit;
    }


    public TransactionRespDto() {
    }

    public List<RespTransaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<RespTransaction> transactionList) {
        this.transactionList = transactionList;
    }

    public TransactionRespDto(Long cusId, BigDecimal totalDebit, BigDecimal totalCredit, List<RespTransaction> transactionList) {
        this.cusId = cusId;
        this.totalDebit = totalDebit;
        this.totalCredit = totalCredit;
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "TransactionRespDto{" +
                "cusId=" + cusId +
                ", totalDebit=" + totalDebit +
                ", totalCredit=" + totalCredit +
                ", transactionList=" + transactionList +
                '}';
    }
}
