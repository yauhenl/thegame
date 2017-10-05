package com.yauhenl.thegame.service;

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

    public World createWorld() {
        World newWorld = new World();
        newWorld.init();
        lastWorldId++;
        newWorld.setId(lastWorldId);
        worlds.put(lastWorldId, newWorld);
        return newWorld;
    }

    public Data getData(Integer worldId, Integer bloopId) {
        World world = getById(worldId);
        if (world != null) {
            return world.getData(bloopId);
        }
        return null;
    }

    @Scheduled(fixedDelay = 1000)
    public void update() {
        worlds.values().forEach(World::update);
    }
}
