package com.eddie.serversentevent.listener;

import com.eddie.serversentevent.service.SseEmitterService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    private final SseEmitterService emitterService;

    @Autowired
    public SessionListener(final SseEmitterService emitterService){
        this.emitterService = emitterService;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        final String sessionId = se.getSession().getId();
        emitterService.removeEmitter(sessionId);
    }
}
