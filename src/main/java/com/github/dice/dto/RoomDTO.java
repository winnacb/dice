package com.github.dice.dto;

import com.github.dice.entity.Player;
import com.github.dice.entity.RoomOwner;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class RoomDTO {

    private String roomName;

    private String roomNumber;

    private String roomPwd;

    private String playerCount;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
