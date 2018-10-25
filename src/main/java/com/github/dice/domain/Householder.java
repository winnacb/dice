package com.github.dice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Householder {

    private int householderId;

    private String userName;

    private String pwd;

    public int getHouseholderId() {
        return householderId;
    }

    public void setHouseholderId(int householderId) {
        this.householderId = householderId;
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
