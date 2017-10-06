package com.yauhenl.thegame.web;

import com.yauhenl.thegame.objects.*;
import com.yauhenl.thegame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private UserWS userWS;

    @GetMapping("/game")
    public String openWorld(@RequestParam Integer id, Model model, HttpSession session) {
        World world = gameService.getById(id);
        Bloop bloop;
        Integer worldInSession = (Integer) session.getAttribute(worldIdAttribute);
        if (world != null) {
            if (worldInSession != null && id.equals(worldInSession)) {
                Integer bloopId = (Integer) session.getAttribute(bloopIdAttribute);
                if (bloopId == null) {
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
        userWS.setWorldId(worldId);
        userWS.setBloopId(bloopId);
        World world = gameService.getById(worldId);
        if (world != null) {
            Bloop bloop = world.getBloopById(bloopId);
            if (bloop != null) {
                bloop.move(new PVector(json.getInt("x"), json.getInt("y")));
            }
        }
        return gameService.getData(userWS.getWorldId(), userWS.getBloopId());
    }
}
