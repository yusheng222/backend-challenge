package com.sheng.backendchallenge.dto;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {

    private String identifier;

    private String currency_amount;

    private String IBAN;

    private LocalDate value_date;

    private String description;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCurrency_amount() {
        return currency_amount;
    }

    public void setCurrency_amount(String currency_amount) {
        this.currency_amount = currency_amount;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public LocalDate getValue_date() {
        return value_date;
    }

    public void setValue_date(LocalDate value_date) {
        this.value_date = value_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "identifier='" + identifier + '\'' +
                ", currency_amount='" + currency_amount + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", value_date=" + value_date +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(identifier, that.identifier) && Objects.equals(currency_amount, that.currency_amount) && Objects.equals(IBAN, that.IBAN) && Objects.equals(value_date, that.value_date) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, currency_amount, IBAN, value_date, description);
    }
}
