package com.yauhenl.thegame.web;

import com.yauhenl.thegame.objects.Bloop;
import com.yauhenl.thegame.objects.Data;
import com.yauhenl.thegame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/game")
    public String openWorld(@RequestParam Integer id, Model model) {
        Bloop bloop = gameService.getById(id).addBloop();
        model.addAttribute("worldId", id);
        model.addAttribute("bloopId", bloop.getId());
        return "game";
    }

    @GetMapping("/getData/{worldId}/{bloopId}")
    @ResponseBody
    public Data getData(@PathVariable Integer worldId, @PathVariable Integer bloopId) {
        return gameService.getData(worldId, bloopId);
    }

    @PostMapping(value = "/move")
    @ResponseStatus(value = HttpStatus.OK)
    public void move(@RequestParam Integer worldId,
                     @RequestParam Integer bloopId,
                     @RequestParam Integer x,
                     @RequestParam Integer y) {
        System.out.println(x + " " + y);
    }
}
