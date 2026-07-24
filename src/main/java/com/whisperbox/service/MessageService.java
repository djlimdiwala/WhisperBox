package com.whisperbox.service;

import com.whisperbox.config.WhisperBoxProperties;
import com.whisperbox.dto.SendMessageRequest;
import com.whisperbox.entity.Message;
import com.whisperbox.repository.MessageRepository;
import org.springframework.stereotype.Service;
import com.whisperbox.dto.MessageResponse;

import java.util.List;
import java.util.stream.Collectors;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private final MessageRepository repository;
    private final WhisperBoxProperties properties;

    public MessageService(MessageRepository repository,
                          WhisperBoxProperties properties) {
        this.repository = repository;
        this.properties = properties;
    }

    public void send(String userKey, SendMessageRequest request) {

        Message message = new Message();

        message.setSender(properties.sender(userKey));
        message.setReceiver(properties.receiver(userKey));

        message.setMessage(request.getMessage());

        message.setCreatedAt(LocalDateTime.now());

        message.setExpiresAt(LocalDateTime.now().plusDays(30));

        repository.save(message);
    }
    public List<MessageResponse> getMessages(String receiver) {

    return repository.findByReceiverOrderByCreatedAtAsc(receiver)
            .stream()
            .map(message -> new MessageResponse(
                    message.getId(),
                    message.getSender(),
                    message.getMessage(),
                    message.getCreatedAt()
            ))
            .collect(Collectors.toList());
    }
}