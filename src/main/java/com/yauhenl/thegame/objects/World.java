package com.yauhenl.thegame.objects;

import processing.core.PVector;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class World {
    private Integer id;
    private Integer width = 10000;
    private Integer height = 10000;
    private Map<Integer, Food> food = new ConcurrentHashMap<>();
    private Map<Integer, Bloop> bloops = new ConcurrentHashMap<>();
    private Integer lastFoodId = 0;
    private Integer lastBloopId = 0;
    private Random r = new Random();

    public static final Integer screenWidth = 1000;
    public static final Integer screenHeight = 600;

    public void init() {
        for (int i = 0; i < 1000; i++) {
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
        if (r.nextFloat() < 0.5f) {
            addFood();
        }
    }

    public Data getData(Bloop bloop) {
        Data result = new Data();
        PVector center = bloop.getLocation();
        Float maxX = center.x + screenWidth / 2;
        Float maxY = center.y + screenHeight / 2;
        Float minX = center.x - screenWidth / 2;
        Float minY = center.y - screenHeight / 2;
        if (maxX > width) maxX = width.floatValue();
        if (maxY > height) maxY = height.floatValue();
        if (minX < 0) minX = 0f;
        if (minY < 0) minY = 0f;
        result.getFood().addAll(selectFood(maxX, maxY, minX, minY));
        result.getBloops().addAll(selectBloops(bloop.getId(), maxX, maxY, minX, minY));
        return result;
    }

    private List<Food> selectFood(Float maxX, Float maxY, Float minX, Float minY) {
        List<Food> resultFood = new ArrayList<>();
        food.values().forEach(it -> {
            PVector loc = it.getLocation();
            if (loc.x > minX && loc.y > minY && loc.x < maxX && loc.y < maxY) {
                resultFood.add(it);
            }
        });
        resultFood.forEach(it -> {
            PVector loc = it.getLocation();
            if (loc.x > screenWidth) loc.x = loc.x - minX;
            if (loc.y > screenHeight) loc.y = loc.y - minY;
            System.out.println(it.getLocation());
        });
        return resultFood;
    }

    private List<Bloop> selectBloops(Integer bloopId, Float maxX, Float maxY, Float minX, Float minY) {
        List<Bloop> resultBloops = new ArrayList<>();
        for (Bloop it : bloops.values()) {
            PVector loc = it.getLocation();
            if (bloopId.equals(it.getId())) {
                Bloop copy = it.copy();
                loc = copy.getLocation();
                loc.x = screenWidth / 2;
                loc.y = screenHeight / 2;
                resultBloops.add(copy);
            } else {
                if (loc.x > minX && loc.y > minY && loc.x < maxX && loc.y < maxY) {
                    resultBloops.add(it);
                }
            }
        }
        resultBloops.forEach(it -> {
            PVector loc = it.getLocation();
            if (loc.x > screenWidth) loc.x = loc.x - minX;
            if (loc.y > screenHeight) loc.y = loc.y - minY;
        });
        return resultBloops;
    }

    public Bloop getBloopById(Integer id) {
        return bloops.get(id);
    }
}
