package com.github.dice.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OpenRoomDTO {

    private String roomNumber;

    private String roomName;

    private String roomPwd;

    private String playerCount;

    private String roomOwnerName;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomPwd() {
        return roomPwd;
    }

    public void setRoomPwd(String roomPwd) {
        this.roomPwd = roomPwd;
    }

    public String getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(String playerCount) {
        this.playerCount = playerCount;
    }

    public String getRoomOwnerName() {
        return roomOwnerName;
    }

    public void setRoomOwnerName(String roomOwnerName) {
        this.roomOwnerName = roomOwnerName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
