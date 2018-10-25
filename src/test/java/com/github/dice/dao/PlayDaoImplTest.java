package com.github.dice.dao;

import com.github.dice.DiceApplication;
import com.github.dice.data.PlayData;
import com.github.dice.domain.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PlayDaoImplTest {

    @Resource
    private PlayDao playDao;

    @Test
    public void addPlayer() {
        for (Player player: PlayData.players) {
            playDao.addPlayer(player);
        }
    }

    @Test
    public void findByUserName() {

    }


}