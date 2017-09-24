package com.yauhenl.thegame;

import com.yauhenl.thegame.game.World;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        new World().init();
        SpringApplication.run(Application.class, args);
    }
}