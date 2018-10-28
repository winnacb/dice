package com.github.dice.dao;

import com.github.dice.domain.Room;
import com.github.dice.domain.RoomOwner;
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

    private static String USER_NAME = "userName";

    private static String PWD = "pwd";

   private Logger logger = LoggerFactory.getLogger(RoomDao.class);

    @Value("${data.dir}")
    private String path;

    public void addRoomOwner(RoomOwner roomOwner) throws DocumentException, IOException {
        String path = getXmlPath();
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new File(path));
        Element root = doc.getRootElement();

        Element roomOwnerElement = root.addElement("roomOwner");
        roomOwnerElement.addAttribute("id", String.valueOf(roomOwner.getId()));

        Element userName = roomOwnerElement.addElement(USER_NAME);
        userName.setText(roomOwner.getUserName());

        Element pwd = roomOwnerElement.addElement(PWD);
        pwd.setText(roomOwner.getPwd());

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
                roomOwner.setId(element.attribute(0).getValue());
                for (Element item : (List<Element>) element.content()) {
                    if (item.getName().equalsIgnoreCase(USER_NAME)) {
                        roomOwner.setUserName(element.getText());
                    } else if (item.getName().equalsIgnoreCase(PWD)) {
                        roomOwner.setPwd(element.getText());
                    }
                }
                roomOwners.add(roomOwner);
            }
        } catch (Exception e) {
            logger.warn("playerDao selectAll is exception", e);
        }
        return roomOwners;
    }


    private String getXmlPath() {
        return path + "/openRoom.xml";
    }

}
