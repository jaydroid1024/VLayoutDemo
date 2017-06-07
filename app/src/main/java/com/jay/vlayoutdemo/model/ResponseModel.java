package com.jay.vlayoutdemo.model;

/**
 * Created by Administrator on 2016/11/16.
 */

public class ResponseModel<T> {
    private T landingList;
    private int status;
    private int code;
    private String errorMessage;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getLandingList() {
        return landingList;
    }

    public void setLandingList(T landingList) {
        this.landingList = landingList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
