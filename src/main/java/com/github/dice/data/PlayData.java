package com.github.dice.data;

import com.github.dice.domain.Player;
import com.github.dice.util.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayData {

    public static List<Player> players = new ArrayList<>(10);


    static {
        Player emma = new Player();
        emma.setPwd("123");
        emma.setPlayerId(1);
        emma.setUserName("emma");
        players.add(emma);

        Player jacob = new Player();
        jacob.setPwd("123");
        jacob.setUserName("jacob");
        jacob.setPlayerId(2);
        players.add(jacob);

        Player emily = new Player();
        emily.setPwd("123");
        emily.setPlayerId(3);
        emily.setUserName("emily");
        players.add(emily);

        Player william = new Player();
        william.setPlayerId(4);
        william.setUserName("william");
        william.setPwd("123");
        players.add(william);

        Player hannah = new Player();
        hannah.setPlayerId(5);
        hannah.setUserName("hannah");
        hannah.setPwd("123");
        players.add(hannah);
    }

}
