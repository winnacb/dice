package com.github.dice.dao;

import com.github.dice.entity.RoomOwner;
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
public class RoomOwnerDao {

    private static String ROOM_OWNER_NAME = "ROOM_OWNER_NAME";

    private static String ROOM_OWNER_PWD = "roomOwnerPwd";

    private Logger logger = LoggerFactory.getLogger(RoomDao.class);

    @Value("${data.dir}")
    private String path;

    public void addRoomOwner(RoomOwner roomOwner) throws DocumentException, IOException {
        String path = getXmlPath();
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new File(path));
        Element root = doc.getRootElement();

        Element roomOwnerElement = root.addElement("roomOwner");

        Element userName = roomOwnerElement.addElement(ROOM_OWNER_NAME);
        userName.setText(roomOwner.getRoomOwnerName());

        Element pwd = roomOwnerElement.addElement(ROOM_OWNER_PWD);
        pwd.setText(roomOwner.getRoomOwnerPwd());

        XMLWriter output = new XMLWriter(new FileWriter(new File(path)));
        output.write(doc);
        output.close();
    }

    public List<RoomOwner> selectAll() {
        List<RoomOwner> roomOwners = new ArrayList<>();
        try {
            String path = getXmlPath();
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(new File(path));
            List<Element> list = (List<Element>) doc.selectNodes("/roomOwners/roomOwner");
            for (Element element : list) {
                RoomOwner roomOwner = new RoomOwner();
                for (Element item : (List<Element>) element.content()) {
                    if (item.getName().equalsIgnoreCase(ROOM_OWNER_NAME)) {
                        roomOwner.setRoomOwnerName(item.getText());
                    } else if (item.getName().equalsIgnoreCase(ROOM_OWNER_PWD)) {
                        roomOwner.setRoomOwnerPwd(item.getText());
                    }
                }
                roomOwners.add(roomOwner);
            }
        } catch (Exception e) {
            logger.warn("RoomOwnerDao selectAll is exception", e);
        }
        return roomOwners;
    }


    private String getXmlPath() {
        return path + "/roomOwner.xml";
    }

}
