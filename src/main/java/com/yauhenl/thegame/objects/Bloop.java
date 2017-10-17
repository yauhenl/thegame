package com.yauhenl.thegame.objects;

import processing.core.PVector;

import java.util.Map;

import static com.yauhenl.thegame.objects.World.*;

public class Bloop extends GameObject {
    private PVector velocity = new PVector(0, 0);
    private PVector acceleration = new PVector(0.0f, 0.0f);
    private Integer mass = 400;

    Bloop(Integer id, PVector location) {
        super(id, location);
    }

    private void setVelocity(PVector velocity) {
        this.velocity = velocity;
    }

    private void setAcceleration(PVector acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public Integer getSize() {
        return mass / 10;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    public void update(Map<Integer, Food> food, Map<Integer, Bloop> bloops) {
        food.keySet().stream().map(food::get).forEach(it -> {
            float dist = PVector.dist(getLocation(), it.getLocation());
            if (dist < getSize() / 2) {
                food.remove(it.getId());
                mass++;
            }
        });

        bloops.keySet().stream().map(bloops::get).forEach(it -> {
            float dist = PVector.dist(getLocation(), it.getLocation());
            if (getSize() > it.getSize() && dist < (getSize() - it.getSize()) / 2) {
                bloops.remove(it.getId());
                mass = mass + it.getMass();
            }
        });
    }

    private void applyForce(PVector force) {
        PVector f = PVector.div(force, mass / 35);
        acceleration.add(f);
    }

    public void move(PVector force) {
        applyForce(new PVector((force.x - screenWidth / 2) / 100, (force.y - screenHeight / 2) / 100));
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
        Bloop bloop = new Bloop(getId(), new PVector(loc.x, loc.y));
        bloop.setVelocity(velocity);
        bloop.setAcceleration(acceleration);
        bloop.setMass(mass);
        return bloop;
    }
}
