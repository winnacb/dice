package com.github.dice.init;

import com.github.dice.entity.RoomOwner;

import java.util.ArrayList;
import java.util.List;

public class RoomOwnerData {

    public static List<RoomOwner> roomOwners = new ArrayList<>(5);

    static {
        RoomOwner admin = new RoomOwner();
        admin.setRoomOwnerPwd("123");
        admin.setRoomOwnerName("admin");
        roomOwners.add(admin);

        RoomOwner amanda = new RoomOwner();
        amanda.setRoomOwnerPwd("123");
        amanda.setRoomOwnerName("amanda");
        roomOwners.add(amanda);

        RoomOwner amelia = new RoomOwner();
        amelia.setRoomOwnerPwd("123");
        amelia.setRoomOwnerName("amelia");
        roomOwners.add(amelia);

        RoomOwner amy = new RoomOwner();
        amy.setRoomOwnerName("amy");
        amy.setRoomOwnerPwd("123");
        roomOwners.add(amy);

        RoomOwner anna = new RoomOwner();
        anna.setRoomOwnerName("anna");
        anna.setRoomOwnerPwd("123");
        roomOwners.add(anna);
    }

}
