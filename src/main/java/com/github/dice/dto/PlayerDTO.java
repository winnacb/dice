package com.github.dice.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PlayerDTO {

    private String id;

    private String userName;

    private transient String pwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}