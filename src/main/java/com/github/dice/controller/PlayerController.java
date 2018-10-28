package com.github.dice.controller;

import com.alibaba.fastjson.JSON;
import com.github.dice.constant.ErrorEnums;
import com.github.dice.entity.Player;
import com.github.dice.dto.ResultDTO;
import com.github.dice.service.PlayerService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Resource
    private PlayerService playerService;

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String pwd, HttpServletRequest request) {
        Player player = playerService.getPlayByNameAndPwd(userName, pwd);
        if (!ObjectUtils.isEmpty(player)) {
            request.getSession().setAttribute("player_login_data", player);
            return JSON.toJSONString(new ResultDTO<>(player));
        } else {
            return JSON.toJSONString(new ResultDTO(ErrorEnums.E_5001));
        }
    }


    @PostMapping("/getPlayerBySession")
    public String getPlayerBySession(@RequestParam String userName, HttpServletRequest request) {
        Player player = (Player) request.getSession().getAttribute("player_login_data");
        if (ObjectUtils.isEmpty(player)) {
            return JSON.toJSONString(new ResultDTO<>(ErrorEnums.E_5002));
        } else {
            if (player.getPlayerName().equals(userName)) {
                return JSON.toJSONString(new ResultDTO<>(player));
            } else {
                return JSON.toJSONString(new ResultDTO<>(ErrorEnums.E_5002));
            }
        }
    }

}
