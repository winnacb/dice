package com.github.dice.service;

import com.github.dice.domain.Room;
import com.github.dice.dto.JoinRoomDTO;
import com.github.dice.dto.OpenRoomDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    public List<Room> rooms = new ArrayList<>();

    public Room openRoom(OpenRoomDTO openRoomDTO) {
        Room room = new Room();

        return room;
    }

    public Room joinRoom(JoinRoomDTO joinRoomDTO){
        Room room = new Room();

        return room;
    }
}
