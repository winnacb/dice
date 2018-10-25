package com.github.dice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Home {

    private String homeName;

    private String homeNumber;

    private String pwd;

    private Householder householder;

    private List<Player> playerList;

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Householder getHouseholder() {
        return householder;
    }

    public void setHouseholder(Householder householder) {
        this.householder = householder;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    @Override
    public String toString() {
       return ToStringBuilder.reflectionToString(this);
    }
}
