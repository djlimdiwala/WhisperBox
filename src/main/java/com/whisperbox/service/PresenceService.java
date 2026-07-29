package com.whisperbox.service;

import com.whisperbox.dto.Presence;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PresenceService {

    private final Map<String, Instant> lastPing =
            new ConcurrentHashMap<>();

    public void ping(String user) {

        lastPing.put(
                user.toUpperCase(),
                Instant.now()
        );

    }

    public Presence getPresence(String user) {

        Instant last =
                lastPing.get(user.toUpperCase());

        if(last==null){

            return new Presence(
                    false,
                    null
            );

        }

        boolean online =
                Duration.between(
                        last,
                        Instant.now()
                ).getSeconds()<30;

        return new Presence(
                online,
                last
        );

    }

}