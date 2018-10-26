package com.github.dice.dao;

import com.github.dice.domain.Player;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class PlayerDao {

    Logger logger = LoggerFactory.getLogger(PlayerDao.class);

    @Value("${data.dir}")
    private String path;

    public void addPlayer(Player player) throws DocumentException, IOException {
        String path = getXmlPath();
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new File(path));
        Element root = doc.getRootElement();

        Element playerElement = root.addElement("player");
        playerElement.addAttribute("id", String.valueOf(player.getId()));

        Element userName = playerElement.addElement("userName");
        userName.setText(player.getUserName());

        Element pwd = playerElement.addElement("pwd");
        pwd.setText(player.getPwd());

        XMLWriter output = new XMLWriter(new FileWriter(new File(path)));
        output.write(doc);
        output.close();
    }

    public Map<String, Object> selectAll() {
        try {
            String path = getXmlPath();
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(new File(path));
            Element root = doc.getRootElement();
            List<Element> childElements = root.elements();
            Map<String, Object> mapEle = new HashMap<>();
            return getAllElements(childElements, mapEle);
        } catch (Exception e) {
            logger.warn("playerDao selectAll is exception", e);
        }
        return null;
    }

    private Map<String, Object> getAllElements(List<Element> childElements, Map<String, Object> mapEle) {
        for (Element ele : childElements) {
            mapEle.put(ele.getName(), ele.getText());
            if (ele.elements().size() > 0) {
                mapEle = getAllElements(ele.elements(), mapEle);
            }
        }
        return mapEle;
    }


    private String getXmlPath() {
        return path + "/user.xml";
    }
}
