package com.eddie.serversentevent.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chat/")
public class ChatController {

    private Map<String, SseEmitter> sseEmitterMap = new HashMap<>();

    @GetMapping("start")
    public SseEmitter startChat(@RequestParam(value = "chatId") final String chatId){
        final SseEmitter emitter = new SseEmitter(0L);
        sseEmitterMap.put(chatId, emitter);
        return emitter;
    }

    @PostMapping("send")
    public String sendMessage(@RequestParam(value = "chatId") final String chatId, @RequestParam(value = "message") final String message) throws IOException {
        final SseEmitter emitter = sseEmitterMap.get(chatId);
        emitter.send(message);
        return "SUCCESS";
    }

    @PostMapping("broadcast")
    public String broadcastMessage(@RequestParam(value = "message") final String message) {
        for(final SseEmitter emitter : sseEmitterMap.values()){
            try {
                emitter.send(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "SUCCESS";
    }
}
