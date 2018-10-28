package com.github.dice.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RoomOwner {

    private String roomOwnerName;

    private String roomOwnerPwd;

    public String getRoomOwnerName() {
        return roomOwnerName;
    }

    public void setRoomOwnerName(String roomOwnerName) {
        this.roomOwnerName = roomOwnerName;
    }

    public String getRoomOwnerPwd() {
        return roomOwnerPwd;
    }

    public void setRoomOwnerPwd(String roomOwnerPwd) {
        this.roomOwnerPwd = roomOwnerPwd;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
