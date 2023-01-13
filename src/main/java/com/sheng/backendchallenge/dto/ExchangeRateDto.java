package com.sheng.backendchallenge.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;


public class ExchangeRateDto implements Serializable {

    private String name;

    private String nameDesc;

    private String from;

    private String to;

    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameDesc() {
        return nameDesc;
    }

    public void setNameDesc(String nameDesc) {
        this.nameDesc = nameDesc;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRateDto that = (ExchangeRateDto) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(name, that.name) && Objects.equals(nameDesc, that.nameDesc) && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nameDesc, from, to, price);
    }

    @Override
    public String toString() {
        return "ExchangeRateDto{" +
                "name='" + name + '\'' +
                ", nameDesc='" + nameDesc + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", price=" + price +
                '}';
    }
}
