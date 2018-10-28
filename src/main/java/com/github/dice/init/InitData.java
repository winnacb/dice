package com.github.dice.init;

import com.github.dice.dao.PlayerDao;
import com.github.dice.dao.RoomOwnerDao;
import com.github.dice.data.PlayData;
import com.github.dice.data.RoomOwnerData;
import com.github.dice.domain.Player;
import com.github.dice.domain.RoomOwner;
import org.apache.commons.io.FileUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
@Order(value = 1)
public class InitData implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(InitData.class);

    @Value("${data.dir}")
    private String path;

    @Resource
    private PlayerDao playerDao;

    @Resource
    private RoomOwnerDao roomOwnerDao;

    @Override
    public void run(ApplicationArguments var1) throws Exception {
        createPlayerData();
        createRoomOwner();
    }

    private void createPlayerData() {
        File file = new File(path + "/player.xml");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><players></players>");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            logger.error("create player.xml is error", e);
        }
        try {
            for (Player player : PlayData.players) {
                playerDao.addPlayer(player);
            }
        } catch (DocumentException | IOException e) {
            logger.error("create player data is error", e);
        }
        logger.info("init player data has been completed");
    }

    private void createRoomOwner() {
        File file = new File(path + "/roomOwner.xml");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><roomOwners></roomOwners>");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            logger.error("create roomOwner.xml is error", e);
        }
        try {
            for (RoomOwner roomOwner : RoomOwnerData.roomOwners) {
                roomOwnerDao.addRoomOwner(roomOwner);
            }
        } catch (DocumentException | IOException e) {
            logger.error("create roomOwner data is error", e);
        }
        logger.info("init roomOwner data has been completed");
    }
}
