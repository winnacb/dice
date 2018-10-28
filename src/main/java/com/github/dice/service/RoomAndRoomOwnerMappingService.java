package com.github.dice.service;

import com.github.dice.dao.RoomAndRoomOwnerMappingDao;
import com.github.dice.entity.RoomAndRoomOwnerMapping;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RoomAndRoomOwnerMappingService {

    @Resource
    private RoomAndRoomOwnerMappingDao roomAndRoomOwnerMappingDao;

    @Resource
    private RoomAndRoomOwnerMappingService roomAndRoomOwnerMappingService;

    public RoomAndRoomOwnerMapping addRoomAndRoomOwnerMapping(RoomAndRoomOwnerMapping roomAndRoomOwnerMapping) throws IOException, DocumentException {
        RoomAndRoomOwnerMapping result = roomAndRoomOwnerMappingService.getRoomAndRoomOwnerMappingBy(roomAndRoomOwnerMapping.getRoomNumber(), roomAndRoomOwnerMapping.getRoomOwnerName());
        if (ObjectUtils.isEmpty(result)) {
            roomAndRoomOwnerMappingDao.addRoomAndRoomOwnerMapping(roomAndRoomOwnerMapping);
            return roomAndRoomOwnerMapping;
        } else {
            return result;
        }
    }

    public RoomAndRoomOwnerMapping getRoomAndRoomOwnerMappingBy(@Nonnull String roomNumber, @Nonnull String roomOwnerName) {
        List<RoomAndRoomOwnerMapping> roomAndRoomOwnerMappings = roomAndRoomOwnerMappingDao.selectAll();
        Optional<RoomAndRoomOwnerMapping> optionalRoomAndRoomOwnerMapping = roomAndRoomOwnerMappings.stream().filter(r -> r.getRoomNumber().equalsIgnoreCase(roomNumber) && r.getRoomOwnerName().equalsIgnoreCase(roomOwnerName)).findFirst();
        return optionalRoomAndRoomOwnerMapping.orElse(null);
    }
}
