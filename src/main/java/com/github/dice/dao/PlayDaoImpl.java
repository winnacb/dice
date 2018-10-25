package com.github.dice.dao;

import com.github.dice.domain.Player;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Objects;

@Component
public class PlayDaoImpl implements PlayDao {

    @Override
    public void addPlayer(Player player) {
        String path = getXmlPath();
        try (Writer out = new PrintWriter(path, "UTF-8")) {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(path);
            Element root = doc.getRootElement();
            Element userElement = root.addElement("player");
            userElement.addAttribute("userName", player.getUserName());
            userElement.addAttribute("password", player.getPwd());
            userElement.addAttribute("playerId", String.valueOf(player.getPlayerId()));
            OutputFormat format = new OutputFormat("\t", true);
            format.setTrimText(true);
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(doc);
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Player findByUserName(String userName) {
        try {
            String path = getXmlPath();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(path);
            String xpath = "//user[@username='" + userName + "']";
            Element userEle = (Element) doc.selectSingleNode(xpath);
            if (userEle == null) {
                return null;
            }
            Player player = new Player();
            player.setUserName(userEle.attributeValue("userName"));
            player.setPwd(userEle.attributeValue("pwd"));
            player.setPlayerId(Integer.parseInt(userEle.attributeValue("playerId")));
            return player;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getXmlPath() {
       return  Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("/")).getPath();
    }
}
