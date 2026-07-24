package com.whisperbox.dto;

import jakarta.validation.constraints.NotBlank;

public class SendMessageRequest {

    @NotBlank
    private String message;

    public SendMessageRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}