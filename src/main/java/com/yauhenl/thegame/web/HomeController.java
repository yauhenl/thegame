package com.yauhenl.thegame.web;

import com.yauhenl.thegame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/")
    public String get(Model model) {
        model.addAttribute("worlds", gameService.findAll());
        return "home";
    }

    @GetMapping("/createWorld")
    public String createWorld() {
        gameService.createWorld();
        return "redirect:/";
    }
}
