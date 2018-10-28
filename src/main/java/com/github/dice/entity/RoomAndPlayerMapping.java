package com.github.dice.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RoomAndPlayerMapping {

    private String roomNumber;

    private String playerName;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
