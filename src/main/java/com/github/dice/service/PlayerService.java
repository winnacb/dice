package com.github.dice.service;

import com.github.dice.domain.Player;
import com.github.dice.data.PlayData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    public Player login(String userName, String pwd) {
        for (Player player : PlayData.players) {
            if (StringUtils.equalsIgnoreCase(player.getUserName(), userName) && StringUtils.equalsIgnoreCase(player.getPwd(), pwd)) {
                return player;
            }
        }
        return null;
    }

    public Player getPlayById(String userId) {
        for (Player player : PlayData.players) {
            if (player.getId().equalsIgnoreCase(userId)) {
                return player;
            }
        }
        return null;
    }

}
