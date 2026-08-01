package com.whisperbox.dto;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public class SendMessageRequest {

    @Schema(description = "Message to send")
    @NotBlank(message = "Message cannot be blank")
    private String message;
    private String messageType = "TEXT";


    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public SendMessageRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}