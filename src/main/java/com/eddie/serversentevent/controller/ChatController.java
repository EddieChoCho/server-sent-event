package com.eddie.serversentevent.controller;

import com.eddie.serversentevent.service.SseEmitterService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/chat/")
public class ChatController {

    private final SseEmitterService emitterService;

    public ChatController(final SseEmitterService emitterService){
        this.emitterService = emitterService;
    }

    @GetMapping("start")
    public SseEmitter startChat(final HttpSession session){
        return emitterService.getEmitter(session);
    }

    @PostMapping("send")
    public String sendMessage(final HttpSession session, @RequestParam(value = "message") final String message) throws IOException {
        emitterService.sendMessage(session, message);
        return "SUCCESS";
    }

    @PostMapping("broadcast")
    public String broadcastMessage(@RequestParam(value = "message") final String message) throws InterruptedException {
        emitterService.broadcastMessage(message);
        return "SUCCESS";
    }

}
