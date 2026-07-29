package com.whisperbox.dto;

import java.time.LocalDateTime;

public record Presence(

        boolean online,

        LocalDateTime lastSeen

) {}