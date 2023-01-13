package com.sheng.backendchallenge.dto;

import com.sheng.backendchallenge.constant.CommonStatusEnum;
import lombok.Data;


public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static <T> ResponseResult success(){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(CommonStatusEnum.SUCCESS.getCode());
        responseResult.setMessage(CommonStatusEnum.SUCCESS.getValue());
        return responseResult;
    }

    public static <T> ResponseResult success(T data){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(CommonStatusEnum.SUCCESS.getCode());
        responseResult.setMessage(CommonStatusEnum.SUCCESS.getValue());
        responseResult.setData(data);
        return responseResult;
    }

    /**
     * fail
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(T data){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(data);
        return responseResult;
    }


    /**
     * fail: customised fail with code, message,
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(int code, String message){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(code);
        responseResult.setMessage(message);
        return responseResult;
    }
    /**
     * fail: customised fail with code, message, specified error
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(int code, String message,String data){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(code);
        responseResult.setMessage(message);
        responseResult.setData(data);
        return responseResult;
    }

}
