package com.sheng.backendchallenge.dto;

import lombok.Data;

import java.util.Date;

@Data
public class KafkaTransactionDto {
    private String uniIdentifier;

    private String amountCur;

    private String accountIban;

    private Date valueDate;

    private String description;

}
