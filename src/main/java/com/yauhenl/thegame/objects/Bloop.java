package com.yauhenl.thegame.objects;

import processing.core.PVector;

import java.util.Map;

import static com.yauhenl.thegame.objects.World.*;

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

    public void setVelocity(PVector velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(PVector acceleration) {
        this.acceleration = acceleration;
    }

    public void update(Map<Integer, Food> food, Map<Integer, Bloop> bloops) {
        food.keySet().stream().map(food::get).forEach(it -> {
            float dist = PVector.dist(getLocation(), it.getLocation());
            if (dist < getSize() / 2) {
                food.remove(it.getId());
                setSize(getSize() + 1);
            }
        });

        bloops.keySet().stream().map(bloops::get).forEach(it -> {
            float dist = PVector.dist(getLocation(), it.getLocation());
            if (getSize() > it.getSize() && dist < (getSize() - it.getSize()) / 2) {
                bloops.remove(it.getId());
                setSize(getSize() + it.getSize());
            }
        });
    }

    private void applyForce(PVector force) {
        PVector f = PVector.div(force, getSize() / 35);
        System.out.println(f);
        acceleration.add(f);
    }

    public void move(PVector force) {
        applyForce(force.x > screenWidth / 2 ? leftForce : rightForce);
        applyForce(force.y > screenHeight / 2 ? upForce : downForce);
        velocity.add(acceleration);
        getLocation().add(velocity);
        acceleration.mult(0);
        velocity.mult(0.95f);
        if (getLocation().x > worldWidth) getLocation().x = worldWidth;
        if (getLocation().y > worldHeight) getLocation().y = worldHeight;
        if (getLocation().x < 0) getLocation().x = 0;
        if (getLocation().y < 0) getLocation().y = 0;
    }

    public Bloop copy() {
        PVector loc = getLocation();
        Bloop bloop = new Bloop(getId(), new PVector(loc.x, loc.y), getSize());
        bloop.setVelocity(velocity);
        bloop.setAcceleration(acceleration);
        return bloop;
    }
}
