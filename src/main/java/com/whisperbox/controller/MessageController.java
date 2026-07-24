package com.whisperbox.controller;

import com.whisperbox.dto.MessageResponse;
import com.whisperbox.dto.SendMessageRequest;
import com.whisperbox.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping("/{userKey}")
    public void send(
            @PathVariable String userKey,
            @Valid @RequestBody SendMessageRequest request) {

        service.send(userKey, request);
    }

    @GetMapping("/{receiver}")
    public List<MessageResponse> getMessages(
            @PathVariable String receiver) {

        return service.getMessages(receiver);
    }
}