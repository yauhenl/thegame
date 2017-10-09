package com.yauhenl.thegame.service;

import com.yauhenl.thegame.objects.Bloop;
import com.yauhenl.thegame.objects.Data;
import com.yauhenl.thegame.objects.World;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {
    private Map<Integer, World> worlds = new HashMap<>();
    private Integer lastWorldId = 0;

    public World getById(Integer id) {
        return worlds.get(id);
    }

    public Collection<World> findAll() {
        return worlds.values();
    }

    public void createWorld() {
        World newWorld = new World();
        newWorld.init();
        lastWorldId++;
        newWorld.setId(lastWorldId);
        worlds.put(lastWorldId, newWorld);
    }

    public Data getData(World world, Bloop bloop) {
        return world.getData(bloop);
    }

    @Scheduled(fixedDelay = 100)
    public void update() {
        worlds.values().forEach(World::update);
    }
}
