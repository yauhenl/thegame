package com.yauhenl.thegame.objects;

public class Bloop extends GameObject {
    private Integer size;

    public Bloop(Integer id, Integer x, Integer y, Integer size) {
        super(id, x, y);
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
