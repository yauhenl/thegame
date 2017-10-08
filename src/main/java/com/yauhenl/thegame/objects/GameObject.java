package com.yauhenl.thegame.objects;

import processing.core.PVector;

public abstract class GameObject {
    private Integer id;
    private PVector location;
    private Integer size;

    public GameObject(Integer id, PVector location, Integer size) {
        this.id = id;
        this.location = location;
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PVector getLocation() {
        return location;
    }

    public void setLocation(PVector location) {
        this.location = location;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
