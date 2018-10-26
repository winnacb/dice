package com.github.dice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Room {

    private String id;

    private String roomName;

    private String roomNumber;

    private String pwd;

    private RoomOwner roomOwner;

    private List<Player> playerList;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public RoomOwner getRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(RoomOwner roomOwner) {
        this.roomOwner = roomOwner;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
       return ToStringBuilder.reflectionToString(this);
    }
}
