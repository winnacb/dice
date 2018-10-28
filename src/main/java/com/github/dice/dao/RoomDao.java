package com.github.dice.dao;

import com.github.dice.entity.Room;
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
public class RoomDao {

    private static final String ROOM_NAME = "roomName";

    private static final String ROOM_PWD = "roomPwd";

    private static final String ROOM_NUMBER = "roomNumber";

    private Logger logger = LoggerFactory.getLogger(RoomDao.class);

    @Value("${data.dir}")
    private String path;

    public void addRoom(Room room) throws DocumentException, IOException {
        String path = getXmlPath();
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new File(path));
        Element root = doc.getRootElement();

        Element roomElement = root.addElement("room");

        Element roomName = roomElement.addElement(ROOM_NAME);
        roomName.setText(room.getRoomName());

        Element pwd = roomElement.addElement(ROOM_PWD);
        pwd.setText(room.getRoomPwd());

        Element roomNumber = roomElement.addElement(ROOM_NUMBER);
        roomNumber.setText(room.getRoomNumber());

        XMLWriter output = new XMLWriter(new FileWriter(new File(path)));
        output.write(doc);
        output.close();
    }

    public List<Room> selectAll() {
        List<Room> rooms = new ArrayList<>();
        try {
            String path = getXmlPath();
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(new File(path));
            List<Element> list = (List<Element>) doc.selectNodes("/rooms/room");
            for (Element element : list) {
                Room room = new Room();
                for (Element item : (List<Element>) element.content()) {
                    if (item.getName().equalsIgnoreCase(ROOM_NAME)) {
                        room.setRoomName(element.getText());
                    } else if (item.getName().equalsIgnoreCase(ROOM_PWD)) {
                        room.setRoomPwd(element.getText());
                    } else if (item.getName().equalsIgnoreCase(ROOM_NUMBER)) {
                        room.setRoomNumber(element.getText());
                    }
                }
                rooms.add(room);
            }
        } catch (Exception e) {
            logger.warn("RoomDao selectAll is exception", e);
        }
        return rooms;
    }


    private String getXmlPath() {
        return path + "/room.xml";
    }
}
