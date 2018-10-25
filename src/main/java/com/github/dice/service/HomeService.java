package com.github.dice.service;

import com.github.dice.domain.Home;
import com.github.dice.dto.JoinHomeDTO;
import com.github.dice.dto.OpenHomeDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    public List<Home> homes  = new ArrayList<>();

    public Home openHome(OpenHomeDTO openHomeDTO) {
        Home home = new Home();

        return home;
    }

    public Home joinHome(JoinHomeDTO joinHomeDTO){
        Home home = new Home();

        return home;
    }
}
