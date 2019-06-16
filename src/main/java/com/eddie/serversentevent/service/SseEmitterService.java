package com.eddie.serversentevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class SseEmitterService {

    private final Long DEFAULT_TIMEOUT;

    private final Map<String, SseEmitter> sseEmitterMap = new HashMap<>();

    private final MultiThreadService multiThreadService;

    @Autowired
    public SseEmitterService(@Value("${sse.emitter.timeout}") final Long DEFAULT_TIMEOUT, final MultiThreadService multiThreadService){
        this.DEFAULT_TIMEOUT = DEFAULT_TIMEOUT;
        this.multiThreadService = multiThreadService;
    }

    public SseEmitter getEmitter(final HttpSession session){
        final String key = session.getId();
        SseEmitter emitter = this.sseEmitterMap.get(key);
        if(emitter == null){
            emitter = new SseEmitter(DEFAULT_TIMEOUT);
            sseEmitterMap.put(key, emitter);
        }
        return emitter;
    }

    public void removeEmitter(final String key){
        this.sseEmitterMap.remove(key);
    }

    public void sendMessage(final HttpSession session, final String message) throws IOException {
        final SseEmitter emitter = this.getEmitter(session);
        emitter.send(message);
    }

    public void broadcastMessage(final String message) throws InterruptedException {
        final List taskList = this.getTaskList(message);
        final ExecutorService executorService = this.multiThreadService.getExecutorService();
        executorService.invokeAll(taskList);
    }

    private List getTaskList(final String messgae){
        return this.sseEmitterMap.values().stream()
                .map(emitter -> (Callable) () -> {
                    emitter.send(messgae);
                    return "SUCCESS";
                }).collect(Collectors.toList());
    }

}
