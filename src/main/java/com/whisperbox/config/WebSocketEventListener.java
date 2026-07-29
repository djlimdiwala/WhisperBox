package com.whisperbox.config;

import com.whisperbox.service.PresenceService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketEventListener {

    private final PresenceService presenceService;

    private final Map<String, String> sessions =
            new ConcurrentHashMap<>();

    public WebSocketEventListener(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    @EventListener
    public void connect(SessionConnectEvent event) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(event.getMessage());

        String user =
                accessor.getFirstNativeHeader("user");

        if (user != null) {

            user = user.toUpperCase();

            sessions.put(
                    accessor.getSessionId(),
                    user
            );

            presenceService.userConnected(user);

        }

    }

    @EventListener
    public void disconnect(SessionDisconnectEvent event) {

        String user =
                sessions.remove(event.getSessionId());

        if (user != null) {

            presenceService.userDisconnected(user);

        }

    }

}