package com.github.dice.dao;

import com.github.dice.domain.Player;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PlayerDaoTest {

    Logger logger = LoggerFactory.getLogger(PlayerDaoTest.class);

    @Resource
    private PlayerDao playerDao;

    @Test
    public void addPlayer() throws IOException, DocumentException {
        //for (Player player: PlayData.players) {
        Player player = new Player();
            playerDao.addPlayer(player);
        //}
    }

    @Test
    public void selectAll() {
        Map<String, Object> map = playerDao.selectAll();
        if(!CollectionUtils.isEmpty(map)) {
            for (Map.Entry<String, Object> item : map.entrySet()) {
                logger.info("key:" + item.getKey() + "value:" + item.getValue());
            }
        }
    }


}