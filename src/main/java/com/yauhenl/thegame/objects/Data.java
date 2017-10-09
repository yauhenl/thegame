package com.yauhenl.thegame.objects;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Food> food = new ArrayList<>();
    private List<Bloop> bloops = new ArrayList<>();

    public List<Food> getFood() {
        return food;
    }

    public List<Bloop> getBloops() {
        return bloops;
    }
}
