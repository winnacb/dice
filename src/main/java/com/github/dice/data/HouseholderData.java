package com.github.dice.data;

import com.github.dice.domain.Householder;

import java.util.List;

public class HouseholderData {

    public static List<Householder> householders;

    static {
        Householder alice = new Householder();
        alice.setPwd("123");
        alice.setHouseholderId(1);
        alice.setUserName("emma");
        householders.add(alice);

        Householder amanda = new Householder();
        amanda.setPwd("123");
        amanda.setUserName("amanda");
        amanda.setHouseholderId(2);
        householders.add(amanda);

        Householder amelia = new Householder();
        amelia.setPwd("123");
        amelia.setHouseholderId(3);
        amelia.setUserName("amelia");
        householders.add(amelia);

        Householder amy = new Householder();
        amy.setHouseholderId(4);
        amy.setUserName("amy");
        amy.setPwd("123");
        householders.add(amy);

        Householder anna = new Householder();
        anna.setHouseholderId(5);
        anna.setUserName("anna");
        anna.setPwd("123");
        householders.add(anna);
    }

}
