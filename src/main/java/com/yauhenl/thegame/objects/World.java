package com.yauhenl.thegame.objects;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

public class World {
    private Integer id;
    private Integer width = 1000;
    private Integer height = 600;
    private Map<Integer, Food> food = new HashMap<>();
    private Map<Integer, Bloop> bloops = new HashMap<>();
    private Random r = new Random();

    public void init() {
        for (int i = 0; i < 50; i++) {
            addFood(i, r.nextInt(width), r.nextInt(height));
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addFood() {
        Integer id = food.size() > 0 ? new TreeSet<>(food.keySet()).last() + 1 : 0;
        addFood(id, r.nextInt(width), r.nextInt(height));
    }

    public void addFood(Integer id, Integer x, Integer y) {
        food.put(id, new Food(id, x, y));
    }

    public Bloop addBloop() {
        Integer id = bloops.size() > 0 ? new TreeSet<>(bloops.keySet()).last() + 1 : 0;
        Bloop result = new Bloop(id, r.nextInt(width), r.nextInt(height), 10);
        bloops.put(id, result);
        return result;
    }

    public void update() {
        addFood();
    }

    public Data getData(Integer bloopId) {
        Data result = new Data();
        result.setBloops(bloops);
        result.setFood(food);
        return result;
    }
}
