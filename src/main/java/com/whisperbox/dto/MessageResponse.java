package com.whisperbox.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public class MessageResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "A")
    private String sender;

    @Schema(example = "Hello!")
    private String message;

    @Schema(example = "2026-07-24T12:30:15")
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