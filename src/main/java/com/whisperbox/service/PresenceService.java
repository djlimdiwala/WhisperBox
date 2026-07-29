package com.whisperbox.service;

import com.whisperbox.dto.Presence;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PresenceService {

    private final Map<String, Boolean> onlineUsers =
            new ConcurrentHashMap<>();

    private final Map<String, LocalDateTime> lastSeen =
            new ConcurrentHashMap<>();

    public void userConnected(String user) {

        onlineUsers.put(user, true);

    }

    public void userDisconnected(String user) {

        onlineUsers.put(user, false);

        lastSeen.put(user, LocalDateTime.now());

    }

    public Presence getPresence(String user) {

        return new Presence(

                onlineUsers.getOrDefault(user, false),

                lastSeen.get(user)

        );

    }

}