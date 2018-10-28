package com.github.dice.dao;

import com.github.dice.entity.Player;
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
public class PlayerDao {

    public static final String PLAYER_NAME = "playerName";

    public static final String PLAYER_PWD = "playerPwd";

    private Logger logger = LoggerFactory.getLogger(PlayerDao.class);

    @Value("${data.dir}")
    private String path;

    public void addPlayer(Player player) throws DocumentException, IOException {
        String path = getXmlPath();
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new File(path));
        Element root = doc.getRootElement();

        Element playerElement = root.addElement("player");

        Element userName = playerElement.addElement(PLAYER_NAME);
        userName.setText(player.getPlayerName());

        Element pwd = playerElement.addElement(PLAYER_PWD);
        pwd.setText(player.getPlayerPwd());

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
                for (Element item : (List<Element>) element.content()) {
                    if (item.getName().equalsIgnoreCase(PLAYER_NAME)) {
                        player.setPlayerName(item.getText());
                    } else if (item.getName().equalsIgnoreCase(PLAYER_PWD)) {
                        player.setPlayerPwd(item.getText());
                    }
                }
                players.add(player);
            }
        } catch (Exception e) {
            logger.warn("PlayerDao selectAll is exception", e);
        }
        return players;
    }


    private String getXmlPath() {
        return path + "/player.xml";
    }
}
