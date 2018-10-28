package com.github.dice.dto;

import com.alibaba.fastjson.JSON;
import com.github.dice.constant.ErrorEnums;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.text.MessageFormat;

public class ResultDTO<T> {

    private String result;

    private String bizMsg;

    private String bizCode;

    private T data;

    private String count;

    public ResultDTO(T data) {
        this.result = "success";
        this.data = data;
    }

    public ResultDTO(String bizCode, String bizMsg) {
        this.result = "error";
        this.bizCode = bizCode;
        this.bizMsg = bizMsg;
    }

    public ResultDTO(ErrorEnums errorEnums) {
        this.result = "error";
        this.bizCode = errorEnums.getCode();
        this.bizMsg = errorEnums.getMsg();
    }

    public ResultDTO(ErrorEnums errorEnums, String... args) {
        this.result = "error";
        this.bizCode = errorEnums.getCode();
        this.bizMsg = MessageFormat.format(errorEnums.getMsg(), args);
    }

    public String getResult() {
        return result;
    }

    public String getBizMsg() {
        return bizMsg;
    }

    public String getBizCode() {
        return bizCode;
    }

    public T getData() {
        return data;
    }

    public String getCount() {
        return count;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
