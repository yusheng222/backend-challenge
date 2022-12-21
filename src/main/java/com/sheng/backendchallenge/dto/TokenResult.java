package com.sheng.backendchallenge.dto;

public class TokenResult {

    private String identifier;


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "TokenResult{" +
                "identifier='" + identifier + '\'' +
                '}';
    }
}
