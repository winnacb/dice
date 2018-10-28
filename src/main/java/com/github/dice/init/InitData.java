package com.github.dice.init;

import com.github.dice.dao.PlayerDao;
import com.github.dice.dao.RoomOwnerDao;
import com.github.dice.entity.Player;
import com.github.dice.entity.RoomOwner;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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
        createRoomAndPlayerMappingData();
        createRoomOwner();
    }

    private void createPlayerData() {
        File file = new File(path + "/player.xml");
        createXmlFile(file, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><players></players>", "create player.xml is error");
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
        createXmlFile(file, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><roomOwners></roomOwners>", "create roomOwner.xml is error");
        try {
            for (RoomOwner roomOwner : RoomOwnerData.roomOwners) {
                roomOwnerDao.addRoomOwner(roomOwner);
            }
        } catch (DocumentException | IOException e) {
            logger.error("create roomOwner data is error", e);
        }
        logger.info("init roomOwner data has been completed");
    }

    private void createRoomAndPlayerMappingData() {
        File file = new File(path + "/roomAndPlayerMapping.xml");
        createXmlFile(file, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><rooms></rooms>", "create roomAndPlayerMapping.xml is error");
    }

    private void createRoomAndRoomOwnerMappingData() {
        File file = new File(path + "/roomAndRoomOwnerMapping.xml");
        createXmlFile(file, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><rooms></rooms>", "create roomAndRoomOwnerMapping.xml is error");
    }

    private void createXmlFile(File file, String content, String errorMessage) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            logger.error(errorMessage, e);
        }
    }
}
