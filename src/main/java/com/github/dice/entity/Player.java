package com.github.dice.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Player {

    private String playerName;

    private String playerPwd;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerPwd() {
        return playerPwd;
    }

    public void setPlayerPwd(String playerPwd) {
        this.playerPwd = playerPwd;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
