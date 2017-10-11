package com.yauhenl.thegame.objects;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private Integer worldId;
    private Integer bloopId;
    private List<Food> food = new ArrayList<>();
    private List<Bloop> bloops = new ArrayList<>();

    public List<Food> getFood() {
        return food;
    }

    public List<Bloop> getBloops() {
        return bloops;
    }

    public Integer getWorldId() {
        return worldId;
    }

    public void setWorldId(Integer worldId) {
        this.worldId = worldId;
    }

    public Integer getBloopId() {
        return bloopId;
    }

    public void setBloopId(Integer bloopId) {
        this.bloopId = bloopId;
    }
}
