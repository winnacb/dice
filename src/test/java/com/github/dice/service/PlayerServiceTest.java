package com.github.dice.service;

import com.github.dice.entity.Player;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PlayerServiceTest {

    private Logger logger = LoggerFactory.getLogger(PlayerServiceTest.class);

    @Resource
    private PlayerService playerService;

    @Test
    public void login() {
        Player player = playerService.getPlayByNameAndPwd("emma", "123");
        Assert.assertNotNull(player);
        logger.info(ToStringBuilder.reflectionToString(player));
    }
    
}