package com.whisperbox.dto;

import java.time.Instant;
import java.time.Instant;

public record Presence(

        boolean online,

        Instant lastSeen

) {}