package com.github.dice.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RoomAndRoomOwnerMapping {

    private String roomNumber;

    private String roomName;

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
