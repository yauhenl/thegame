package com.yauhenl.thegame.objects;

import java.util.*;

public class World {
    private Integer id;
    private Integer height;
    private Integer width;
    private Map<Integer, Food> food = new HashMap<>();
    private Map<Integer, Bloop> bloops = new HashMap<>();

    public void init() {
        width = 1000;
        height = 1000;
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            addFood(i, r.nextInt(width), r.nextInt(height));
        }

        for (int i = 0; i < 100; i++) {
            addBloop(i, r.nextInt(width), r.nextInt(height), 10);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addFood(Integer id, Integer x, Integer y) {
        food.put(id, new Food(id, x, y));
    }

    public void addBloop(Integer id, Integer x, Integer y, Integer size) {
        bloops.put(id, new Bloop(id, x, y, size));
    }

    public void update() {

    }

    public Data getData(Integer bloopId) {
        Data result = new Data();
        result.setBloops(bloops);
        result.setFood(food);
        return result;
    }
}
