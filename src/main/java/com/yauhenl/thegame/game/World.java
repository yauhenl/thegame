package com.yauhenl.thegame.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private Integer height;
    private Integer width;
    private List<Food> food = new ArrayList<>();
    private List<Bloop> bloops= new ArrayList<>();

    public void init() {
        width = 300;
        height = 300;
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            addFood(r.nextInt(width), r.nextInt(height));
        }

        for (int i = 0; i < 100; i++) {
            addBloop(r.nextInt(width), r.nextInt(height), 10);
        }
    }

    public void addFood(Integer x, Integer y) {
        food.add(new Food(x, y));
    }

    public void addBloop(Integer x, Integer y, Integer size) {
        bloops.add(new Bloop(x, y, size));
    }
}
