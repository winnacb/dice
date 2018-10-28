package com.github.dice.data;

import com.github.dice.domain.RoomOwner;

import java.util.ArrayList;
import java.util.List;

public class RoomOwnerData {

    public static List<RoomOwner> roomOwners=new ArrayList<>(5);

    static {
        RoomOwner admin = new RoomOwner();
        admin.setPwd("123");
        admin.setUserName("admin");
        admin.setId("1");
        roomOwners.add(admin);

        RoomOwner amanda = new RoomOwner();
        amanda.setPwd("123");
        amanda.setUserName("amanda");
        amanda.setId("2");
        roomOwners.add(amanda);

        RoomOwner amelia = new RoomOwner();
        amelia.setPwd("123");
        amelia.setId("3");
        amelia.setUserName("amelia");
        roomOwners.add(amelia);

        RoomOwner amy = new RoomOwner();
        amy.setId("4");
        amy.setUserName("amy");
        amy.setPwd("123");
        roomOwners.add(amy);

        RoomOwner anna = new RoomOwner();
        anna.setId("5");
        anna.setUserName("anna");
        anna.setPwd("123");
        roomOwners.add(anna);
    }

}
