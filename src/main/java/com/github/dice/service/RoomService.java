package com.github.dice.service;

import com.github.dice.dao.RoomAndPlayerMappingDao;
import com.github.dice.dao.RoomAndRoomOwnerMappingDao;
import com.github.dice.dao.RoomDao;
import com.github.dice.dto.RoomDTO;
import com.github.dice.entity.Room;
import com.github.dice.dto.JoinRoomDTO;
import com.github.dice.dto.OpenRoomDTO;
import com.github.dice.entity.RoomAndRoomOwnerMapping;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private Logger logger = LoggerFactory.getLogger(RoomService.class);

    @Resource
    private RoomDao roomDao;

    @Resource
    private RoomService roomService;

    @Resource
    private RoomAndRoomOwnerMappingService roomAndRoomOwnerMappingService;

    @Resource
    private RoomAndPlayerMappingService roomAndPlayerMappingService;

    public RoomDTO openRoom(OpenRoomDTO openRoomDTO) throws IOException, DocumentException {
        RoomDTO roomDTO = new RoomDTO();
        //1. create room
        Room room = new Room();
        BeanUtils.copyProperties(openRoomDTO,room);
        Room addRoomResult = roomService.addRoom(room);
        //2. create roomAndRoomOwnerMapping
        RoomAndRoomOwnerMapping roomAndRoomOwnerMapping = new RoomAndRoomOwnerMapping();
        BeanUtils.copyProperties(openRoomDTO, roomAndRoomOwnerMapping);
        RoomAndRoomOwnerMapping addMappingResult = roomAndRoomOwnerMappingService.addRoomAndRoomOwnerMapping(roomAndRoomOwnerMapping);
        //3. bind roomDTO

        return roomDTO;
    }

    public RoomDTO joinRoom(JoinRoomDTO joinRoomDTO) {
        RoomDTO roomDTO = new RoomDTO();
        //1. create roomAndPlayerMapping

        return roomDTO;
    }

    public RoomDTO getRoomDTOByRoomNumber(@Nonnull String roomNumber) {
        //get room info

        //get roomOwner info

        //get players in this room
        return null;
    }

    public Room getRoomByNumberAndName(@Nonnull String roomNumber, @Nonnull String roomName) {
        List<Room> roomList = roomDao.selectAll();
        Optional<Room> optionalRoom = roomList.stream().filter(item -> item.getRoomNumber().equalsIgnoreCase(roomNumber)
                && item.getRoomName().equalsIgnoreCase(roomName)).findFirst();
        return optionalRoom.orElse(null);
    }

    public Room getRoomByNumber(@Nonnull String roomNumber) {
        List<Room> roomList = roomDao.selectAll();
        Optional<Room> optionalRoom = roomList.stream().filter(item -> item.getRoomNumber().equalsIgnoreCase(roomNumber)).findFirst();
        return optionalRoom.orElse(null);
    }

    public Room addRoom(Room room) throws IOException, DocumentException {
        Room findRoom = roomService.getRoomByNumberAndName(room.getRoomNumber(), room.getRoomName());
        if (ObjectUtils.isEmpty(findRoom)) {
            roomDao.addRoom(room);
        }
        return findRoom;
    }

}
