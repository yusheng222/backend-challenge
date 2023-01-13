package com.sheng.backendchallenge.dto;

import lombok.Data;

import java.util.Date;
import java.util.Objects;


public class KafkaTransactionDto {
    private String uniIdentifier;

    private String amountCur;

    private String accountIban;

    private Date valueDate;

    private String description;

    public String getUniIdentifier() {
        return uniIdentifier;
    }

    public void setUniIdentifier(String uniIdentifier) {
        this.uniIdentifier = uniIdentifier;
    }

    public String getAmountCur() {
        return amountCur;
    }

    public void setAmountCur(String amountCur) {
        this.amountCur = amountCur;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KafkaTransactionDto that = (KafkaTransactionDto) o;
        return Objects.equals(uniIdentifier, that.uniIdentifier) && Objects.equals(amountCur, that.amountCur) && Objects.equals(accountIban, that.accountIban) && Objects.equals(valueDate, that.valueDate) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniIdentifier, amountCur, accountIban, valueDate, description);
    }

    @Override
    public String toString() {
        return "KafkaTransactionDto{" +
                "uniIdentifier='" + uniIdentifier + '\'' +
                ", amountCur='" + amountCur + '\'' +
                ", accountIban='" + accountIban + '\'' +
                ", valueDate=" + valueDate +
                ", description='" + description + '\'' +
                '}';
    }
}
