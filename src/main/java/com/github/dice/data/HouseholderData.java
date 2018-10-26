package com.github.dice.data;

import com.github.dice.domain.RoomOwner;

import java.util.List;

public class HouseholderData {

    public static List<RoomOwner> roomOwners;

    static {
        RoomOwner alice = new RoomOwner();
        alice.setPwd("123");
        alice.setId(1);
        alice.setUserName("emma");
        roomOwners.add(alice);

        RoomOwner amanda = new RoomOwner();
        amanda.setPwd("123");
        amanda.setUserName("amanda");
        amanda.setId(2);
        roomOwners.add(amanda);

        RoomOwner amelia = new RoomOwner();
        amelia.setPwd("123");
        amelia.setId(3);
        amelia.setUserName("amelia");
        roomOwners.add(amelia);

        RoomOwner amy = new RoomOwner();
        amy.setId(4);
        amy.setUserName("amy");
        amy.setPwd("123");
        roomOwners.add(amy);

        RoomOwner anna = new RoomOwner();
        anna.setId(5);
        anna.setUserName("anna");
        anna.setPwd("123");
        roomOwners.add(anna);
    }

}
