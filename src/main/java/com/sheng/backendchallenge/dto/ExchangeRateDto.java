package com.sheng.backendchallenge.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExchangeRateDto implements Serializable {

    private String name;

    private String nameDesc;

    private String from;

    private String to;

    private double price;
}
