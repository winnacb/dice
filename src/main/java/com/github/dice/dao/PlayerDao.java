package com.github.dice.dao;

import com.github.dice.domain.Player;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class PlayerDao {

   private Logger logger = LoggerFactory.getLogger(PlayerDao.class);

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

    public List<Player> selectAll() {
        List<Player> players = new ArrayList<>();
        try {
            String path = getXmlPath();
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(new File(path));
            List<Element> list = (List<Element>) doc.selectNodes("/players/player");
            for (Element element : list) {
                Player player = new Player();
                player.setId(element.attribute(0).getValue());
                for (Element item : (List<Element>) element.content()) {
                    if (item.getName().equalsIgnoreCase("userName")) {
                        player.setUserName(element.getText());
                    } else if (item.getName().equalsIgnoreCase("pwd")) {
                        player.setPwd(element.getText());
                    }
                }
                players.add(player);
            }
        } catch (Exception e) {
            logger.warn("playerDao selectAll is exception", e);
        }
        return players;
    }


    private String getXmlPath() {
        return path + "/player.xml";
    }
}
