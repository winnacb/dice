package com.github.dice.service;

import com.github.dice.dao.RoomAndPlayerMappingDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoomAndPlayerMappingService {

    @Resource
    private RoomAndPlayerMappingDao roomAndPlayerMappingDao;



}
