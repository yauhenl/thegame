package com.yauhenl.thegame.web;

import com.yauhenl.thegame.objects.Bloop;
import com.yauhenl.thegame.objects.Data;
import com.yauhenl.thegame.objects.World;
import com.yauhenl.thegame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import processing.core.PVector;
import processing.data.JSONObject;

import javax.servlet.http.HttpSession;

@Controller
@EnableWebSocketMessageBroker
public class GameController {

    private static final String worldIdAttribute = "worldId";
    private static final String bloopIdAttribute = "bloopId";

    @Autowired
    private GameService gameService;

    @GetMapping("/game")
    public String openWorld(@RequestParam Integer id, Model model, HttpSession session) {
        World world = gameService.getById(id);
        Bloop bloop;
        Integer worldInSession = (Integer) session.getAttribute(worldIdAttribute);
        if (world != null) {
            if (worldInSession != null && id.equals(worldInSession)) {
                Integer bloopId = (Integer) session.getAttribute(bloopIdAttribute);
                if (bloopId == null || world.getBloopById(bloopId) == null) {
                    bloop = world.addBloop();
                } else {
                    bloop = world.getBloops().get(bloopId);
                }
            } else {
                bloop = world.addBloop();
            }
            model.addAttribute(worldIdAttribute, world.getId());
            model.addAttribute(bloopIdAttribute, bloop.getId());
            session.setAttribute(worldIdAttribute, world.getId());
            session.setAttribute(bloopIdAttribute, bloop.getId());
            return "game";
        } else {
            return "redirect:/";
        }
    }

    @MessageMapping("/move")
    @SendTo("/topic/data")
    public Data update(String message) {
        JSONObject json = JSONObject.parse(message);
        Integer worldId = json.getInt(worldIdAttribute);
        Integer bloopId = json.getInt(bloopIdAttribute);
        World world = gameService.getById(worldId);
        if (world != null) {
            Bloop bloop = world.getBloopById(bloopId);
            if (bloop != null) {
                bloop.move(new PVector(json.getInt("x"), json.getInt("y")));
                Data data = gameService.getData(world, bloop);
                data.setBloopId(bloopId);
                data.setWorldId(worldId);
                return data;
            }
        }
        return null;
    }
}
