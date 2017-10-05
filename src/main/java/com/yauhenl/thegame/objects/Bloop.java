package com.yauhenl.thegame.objects;

import processing.core.PVector;

import java.util.Map;

public class Bloop extends GameObject {
    private static final PVector leftForce = new PVector(0.2f, 0);
    private static final PVector rightForce = new PVector(-0.2f, 0);
    private static final PVector upForce = new PVector(0, 0.2f);
    private static final PVector downForce = new PVector(0, -0.2f);

    private PVector velocity = new PVector(0, 0);
    private PVector acceleration = new PVector(0.0f, 0.0f);

    public Bloop(Integer id, PVector location, Integer size) {
        super(id, location, size);
    }

    public void update(Map<Integer, Food> foods) {
        for (Integer id : foods.keySet()) {
            Food it = foods.get(id);
            float dist = PVector.dist(getLocation(), it.getLocation());
            if (dist < getSize() / 2) {
                foods.remove(it.getId());
                setSize(getSize() + 1);
            }
        }
    }

    private void applyForce(PVector force) {
        PVector f = PVector.div(force, getSize() / 35);
        acceleration.add(f);
    }

    public void move(PVector force) {
        applyForce(force.x > getLocation().x ? leftForce : rightForce);
        applyForce(force.y > getLocation().y ? upForce : downForce);
        velocity.add(acceleration);
        getLocation().add(velocity);
        acceleration.mult(0);
        velocity.mult(0.95f);
    }
}
