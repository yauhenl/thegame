package com.yauhenl.thegame.web;

import com.yauhenl.thegame.objects.Data;
import com.yauhenl.thegame.objects.World;
import com.yauhenl.thegame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/")
    public String get(Map<String, Object> model) {
        model.put("time", new Date());
        return "home";
    }

    @GetMapping("/createWorld")
    @ResponseBody
    public Data createWorld() {
        World world = gameService.createWorld();
        return gameService.getData(world.getId(), 0);
    }

    @GetMapping("/getData/{worldId}/{bloopId}")
    @ResponseBody
    public Data getData(@PathVariable Integer worldId, @PathVariable Integer bloopId) {
        return gameService.getData(worldId, bloopId);
    }
}
