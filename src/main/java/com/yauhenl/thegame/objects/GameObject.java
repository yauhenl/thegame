package com.yauhenl.thegame.objects;

import processing.core.PVector;

public abstract class GameObject {
    protected Integer id;
    protected PVector location;
    protected Integer size;

    GameObject(Integer id, PVector location) {
        this.id = id;
        this.location = location;
    }

    GameObject(Integer id, PVector location, Integer size) {
        this.id = id;
        this.location = location;
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public PVector getLocation() {
        return location;
    }

    public Integer getSize() {
        return size;
    }

    void setSize(Integer size) {
        this.size = size;
    }
}
