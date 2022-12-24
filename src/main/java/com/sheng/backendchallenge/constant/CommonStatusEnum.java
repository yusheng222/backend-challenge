package com.sheng.backendchallenge.constant;

public enum CommonStatusEnum {

    SUCCESS(1,"success"),
    FAIL(0,"fail"),

    MULTIPLE_REQUEST(999,"Bad Request!! Multiple request!!"),
    TOKEN_ERROR(1100,"Token Error!");

    private int code;

    private String value;

    CommonStatusEnum(int code, String value){
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
