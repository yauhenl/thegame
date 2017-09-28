package com.yauhenl.thegame.service;

import com.yauhenl.thegame.objects.Data;
import com.yauhenl.thegame.objects.World;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

@Service
public class GameService {
    private Map<Integer, World> worlds = new HashMap<>();

    public World getById(Integer id) {
        return worlds.get(id);
    }

    public World createWorld() {
        World newWorld = new World();
        newWorld.init();
        Integer id = worlds.size() > 0 ? new TreeSet<>(worlds.keySet()).last() : 0;
        newWorld.setId(id);
        worlds.put(id, newWorld);
        return newWorld;
    }

    public Data getData(Integer worldId, Integer bloopId) {
        return getById(worldId).getData(bloopId);
    }
}
