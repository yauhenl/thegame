package com.yauhenl.thegame.objects;

import processing.core.PVector;

public class Food extends GameObject {

    Food(Integer id, PVector location, Integer size) {
        super(id, location, size);
    }

    public Food copy() {
        PVector loc = getLocation();
        return new Food(getId(), new PVector(loc.x, loc.y), getSize());
    }
}
