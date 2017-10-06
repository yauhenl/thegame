package com.yauhenl.thegame.objects;

import processing.core.PVector;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class World {
    private Integer id;
    private Integer width = 1000;
    private Integer height = 600;
    private Map<Integer, Food> food = new ConcurrentHashMap<>();
    private Map<Integer, Bloop> bloops = new ConcurrentHashMap<>();
    private Integer lastFoodId = 0;
    private Integer lastBloopId = 0;
    private Random r = new Random();

    public void init() {
        for (int i = 0; i < 50; i++) {
            addFood();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Map<Integer, Food> getFood() {
        return food;
    }

    public Map<Integer, Bloop> getBloops() {
        return bloops;
    }

    public void addFood() {
        lastFoodId++;
        food.put(lastFoodId, new Food(lastFoodId, new PVector(r.nextInt(width), r.nextInt(height)), 8));
    }

    public Bloop addBloop() {
        lastBloopId++;
        Bloop result = new Bloop(lastBloopId, new PVector(r.nextInt(width), r.nextInt(height)), 35);
        bloops.put(lastBloopId, result);
        return result;
    }

    public void update() {
        for (Integer bloopId : bloops.keySet()) {
            Bloop it = bloops.get(bloopId);
            if (it != null) {
                it.update(food, bloops);
            }
        }
        if (r.nextFloat() < 0.05f) {
            addFood();
        }
    }

    public Data getData(Integer bloopId) {
        Data result = new Data();
        result.setBloops(bloops);
        result.setFood(food);
        return result;
    }

    public Bloop getBloopById(Integer id) {
        return bloops.get(id);
    }
}
