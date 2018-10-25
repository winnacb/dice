package com.github.dice.dao;

import com.github.dice.domain.Player;

public interface PlayDao {
    void addPlayer(Player player);
    Player findByUserName(String userName);
}
