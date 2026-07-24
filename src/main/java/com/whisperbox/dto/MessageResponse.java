package com.whisperbox.dto;

import java.time.LocalDateTime;

public class MessageResponse {

    private Long id;
    private String sender;
    private String message;
    private LocalDateTime createdAt;

    public MessageResponse(Long id,
                           String sender,
                           String message,
                           LocalDateTime createdAt) {
        this.id = id;
        this.sender = sender;
        this.message = message;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}