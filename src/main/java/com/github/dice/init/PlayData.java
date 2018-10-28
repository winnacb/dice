package com.github.dice.init;

import com.github.dice.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayData {

    public static List<Player> players = new ArrayList<>(10);


    static {
        Player emma = new Player();
        emma.setPlayerPwd("123");
        emma.setPlayerName("emma");
        players.add(emma);

        Player jacob = new Player();
        jacob.setPlayerPwd("123");
        jacob.setPlayerName("jacob");
        players.add(jacob);

        Player emily = new Player();
        emily.setPlayerPwd("123");
        emily.setPlayerName("emily");
        players.add(emily);

        Player william = new Player();
        william.setPlayerName("william");
        william.setPlayerPwd("123");
        players.add(william);

        Player hannah = new Player();
        hannah.setPlayerName("hannah");
        hannah.setPlayerPwd("123");
        players.add(hannah);
    }

}
