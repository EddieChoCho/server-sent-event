package com.eddie.serversentevent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/chatRoom")
    public String greeting() {
        return "chatRoom";
    }
}
