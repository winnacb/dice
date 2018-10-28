package com.github.dice.service;

import com.github.dice.dao.PlayerDao;
import com.github.dice.entity.Player;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Resource
    private PlayerDao playerDao;

    @Resource
    private PlayerService playerService;

    public Player getPlayByNameAndPwd(String playerName, String pwd) {
        List<Player> players = playerDao.selectAll();
        Optional<Player> optionalPlayer = players.stream().filter((player -> StringUtils.equalsIgnoreCase(player.getPlayerName(), playerName)
                && StringUtils.equalsIgnoreCase(player.getPlayerPwd(), pwd))).findFirst();
        return optionalPlayer.orElse(null);
    }

    public Player getPlayerByName(String playerName) {
        List<Player> players = playerDao.selectAll();
        Optional<Player> optionalPlayer = players.stream().filter((player -> StringUtils.equalsIgnoreCase(player.getPlayerName(), playerName))).findFirst();
        return optionalPlayer.orElse(null);
    }

    public void AddPlayer(Player player) throws DocumentException, IOException {
        Player findPlay = playerService.getPlayerByName(player.getPlayerName());
        if (ObjectUtils.isEmpty(findPlay)) {
            playerDao.addPlayer(player);
        }
    }

}
