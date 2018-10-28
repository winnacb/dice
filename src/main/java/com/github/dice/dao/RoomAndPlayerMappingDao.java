package com.github.dice.dao;

import com.github.dice.entity.RoomAndPlayerMapping;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoomAndPlayerMappingDao {

    private Logger logger = LoggerFactory.getLogger(RoomAndPlayerMappingDao.class);

    private static final String ROOM_NUMBER = "roomNumber";

    private static final String PLAYER_NAME = "playerName";

    @Value("${data.dir}")
    private String path;

    private String getXmlPath() {
        return path + "/roomAndPlayerMapping.xml";
    }

    public void addRoomAndPlayerMapping(RoomAndPlayerMapping roomAndPlayerMapping) throws DocumentException, IOException {
        String path = getXmlPath();
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new File(path));
        Element root = doc.getRootElement();

        Element playerElement = root.addElement("room");

        Element playerName = playerElement.addElement(PLAYER_NAME);
        playerName.setText(roomAndPlayerMapping.getPlayerName());

        Element roomNumber = playerElement.addElement(ROOM_NUMBER);
        roomNumber.setText(roomAndPlayerMapping.getRoomNumber());

        XMLWriter output = new XMLWriter(new FileWriter(new File(path)));
        output.write(doc);
        output.close();
    }

    public List<RoomAndPlayerMapping> selectAll() {
        List<RoomAndPlayerMapping> roomAndPlayerMappings = new ArrayList<>();
        try {
            String path = getXmlPath();
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(new File(path));
            List<Element> list = (List<Element>) doc.selectNodes("/rooms/room");
            for (Element element : list) {
                RoomAndPlayerMapping roomAndPlayerMapping = new RoomAndPlayerMapping();
                for (Element item : (List<Element>) element.content()) {
                    if (item.getName().equalsIgnoreCase(ROOM_NUMBER)) {
                        roomAndPlayerMapping.setRoomNumber(item.getText());
                    } else if (item.getName().equalsIgnoreCase(PLAYER_NAME)) {
                        roomAndPlayerMapping.setPlayerName(item.getText());
                    }
                }
                roomAndPlayerMappings.add(roomAndPlayerMapping);
            }
        } catch (Exception e) {
            logger.warn("RoomAndPlayerMappingDao selectAll is exception", e);
        }
        return roomAndPlayerMappings;
    }


}
