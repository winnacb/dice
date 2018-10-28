package com.github.dice.dao;

import com.github.dice.entity.RoomAndRoomOwnerMapping;
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
public class RoomAndRoomOwnerMappingDao {

    public static final String ROOM_OWNER_NAME = "roomOwnerName";

    public static final String ROOM_NUMBER = "roomNumber";

    public static final String ROOM_NAME = "roomName";

    private Logger logger = LoggerFactory.getLogger(RoomAndRoomOwnerMappingDao.class);

    @Value("${data.dir}")
    private String path;

    private String getXmlPath() {
        return path + "/roomAndRoomOwnerMapping.xml";
    }

    public void addRoomAndRoomOwnerMapping(RoomAndRoomOwnerMapping roomAndRoomOwnerMapping) throws DocumentException, IOException {
        String path = getXmlPath();
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new File(path));
        Element root = doc.getRootElement();

        Element playerElement = root.addElement("room");

        Element roomOwnerName = playerElement.addElement(ROOM_OWNER_NAME);
        roomOwnerName.setText(roomAndRoomOwnerMapping.getRoomOwnerName());

        Element roomNumber = playerElement.addElement(ROOM_NUMBER);
        roomNumber.setText(roomAndRoomOwnerMapping.getRoomNumber());

        Element roomName = playerElement.addElement(ROOM_NAME);
        roomName.setText(roomAndRoomOwnerMapping.getRoomName());


        XMLWriter output = new XMLWriter(new FileWriter(new File(path)));
        output.write(doc);
        output.close();
    }

    public List<RoomAndRoomOwnerMapping> selectAll() {
        List<RoomAndRoomOwnerMapping> roomAndRoomOwnerMappings = new ArrayList<>();
        try {
            String path = getXmlPath();
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(new File(path));
            List<Element> list = (List<Element>) doc.selectNodes("/rooms/room");
            for (Element element : list) {
                RoomAndRoomOwnerMapping roomAndRoomOwnerMapping = new RoomAndRoomOwnerMapping();
                for (Element item : (List<Element>) element.content()) {
                    if (item.getName().equalsIgnoreCase(ROOM_OWNER_NAME)) {
                        roomAndRoomOwnerMapping.setRoomOwnerName(item.getText());
                    } else if (item.getName().equalsIgnoreCase(ROOM_NUMBER)) {
                        roomAndRoomOwnerMapping.setRoomNumber(item.getText());
                    } else if (item.getName().equalsIgnoreCase(ROOM_NAME)) {
                        roomAndRoomOwnerMapping.setRoomName(item.getText());
                    }
                }
                roomAndRoomOwnerMappings.add(roomAndRoomOwnerMapping);
            }
        } catch (Exception e) {
            logger.warn("RoomAndRoomOwnerMappingDao selectAll is exception", e);
        }
        return roomAndRoomOwnerMappings;
    }

}
