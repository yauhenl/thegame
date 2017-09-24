package com.yauhenl.thegame.game;

public class Bloop extends GameObject {
    private Integer size;

    public Bloop(Integer x, Integer y, Integer size) {
        super(x, y);
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
