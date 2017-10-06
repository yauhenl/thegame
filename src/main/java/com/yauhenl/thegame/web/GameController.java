package com.yauhenl.thegame.web;

import com.yauhenl.thegame.objects.Bloop;
import com.yauhenl.thegame.objects.Data;
import com.yauhenl.thegame.objects.World;
import com.yauhenl.thegame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import processing.core.PVector;

import javax.servlet.http.HttpSession;

@Controller
public class GameController {

    private static final String worldIdAttribute = "worldId";
    private static final String bloopIdAttribute = "bloopId";

    @Autowired
    private GameService gameService;

    @GetMapping("/game")
    public String openWorld(@RequestParam Integer id, Model model, HttpSession session) {
        World world = gameService.getById(id);
        Integer worldInSession = (Integer) session.getAttribute(worldIdAttribute);
        if (world != null) {
            if (worldInSession != null && id.equals(worldInSession)) {
                model.addAttribute(worldIdAttribute, id);
                Integer bloopId = (Integer) session.getAttribute(bloopIdAttribute);
                if (world.getBloops().containsKey(bloopId)) {
                    model.addAttribute(bloopIdAttribute, bloopId);
                } else {
                    Bloop bloop = world.addBloop();
                    model.addAttribute(bloopIdAttribute, bloop.getId());
                    session.setAttribute(bloopIdAttribute, bloop.getId());
                }
                return "game";
            } else {
                Bloop bloop = world.addBloop();
                model.addAttribute(worldIdAttribute, id);
                model.addAttribute(bloopIdAttribute, bloop.getId());
                session.setAttribute(worldIdAttribute, world.getId());
                session.setAttribute(bloopIdAttribute, bloop.getId());
                return "game";
            }
        } else {
            return "redirect:/";
        }
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
        World world = gameService.getById(worldId);
        if (world != null) {
            Bloop bloop = world.getBloopById(bloopId);
            if (bloop != null) {
                bloop.move(new PVector(x, y));
            }
        }
    }
}
