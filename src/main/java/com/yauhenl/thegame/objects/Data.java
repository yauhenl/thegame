package com.yauhenl.thegame.objects;

import java.util.HashMap;
import java.util.Map;

public class Data {
    private Map<Integer, Food> food = new HashMap<>();
    private Map<Integer, Bloop> bloops = new HashMap<>();

    public Map<Integer, Food> getFood() {
        return food;
    }

    public void setFood(Map<Integer, Food> food) {
        this.food = food;
    }

    public Map<Integer, Bloop> getBloops() {
        return bloops;
    }

    public void setBloops(Map<Integer, Bloop> bloops) {
        this.bloops = bloops;
    }
}
