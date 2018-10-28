package com.github.dice.dao;

import com.github.dice.init.PlayData;
import com.github.dice.entity.Player;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PlayerDaoTest {

    Logger logger = LoggerFactory.getLogger(PlayerDaoTest.class);

    @Resource
    private PlayerDao playerDao;

    @Test
    public void addPlayer() throws IOException, DocumentException {
        for (Player player: PlayData.players) {
            playerDao.addPlayer(player);
        }
    }

    @Test
    public void selectAll() {
        List<Player> list = playerDao.selectAll();
        for (Player player:list) {
            logger.info(player.toString()+";");
        }
    }


}