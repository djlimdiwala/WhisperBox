package com.whisperbox.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

public class MessageResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "A")
    private String sender;

    @Schema(example = "Hello!")
    private String message;

    @Schema(example = "TEXT")
    private String messageType;

    @Schema(example = "2026-07-24T12:30:15Z")
    private Instant createdAt;

    public MessageResponse(
            Long id,
            String sender,
            String message,
            String messageType,
            Instant createdAt) {

        this.id = id;
        this.sender = sender;
        this.message = message;
        this.messageType = messageType;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageType() {
        return messageType;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}