package com.whisperbox.service;

import com.whisperbox.config.WhisperBoxProperties;
import com.whisperbox.dto.MessageResponse;
import com.whisperbox.dto.SendMessageRequest;
import com.whisperbox.entity.Message;
import com.whisperbox.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class MessageService {

    private static final Logger log =
            LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository repository;
    private final WhisperBoxProperties properties;
    private final SimpMessagingTemplate messagingTemplate;

    public MessageService(
            MessageRepository repository,
            WhisperBoxProperties properties,
            SimpMessagingTemplate messagingTemplate) {

        this.repository = repository;
        this.properties = properties;
        this.messagingTemplate = messagingTemplate;
    }

    public void send(String userKey, SendMessageRequest request) {

        Message message = new Message();

        message.setSender(properties.sender(userKey));
        message.setReceiver(properties.receiver(userKey));
        message.setMessage(request.getMessage());
        message.setCreatedAt(Instant.now());
        message.setExpiresAt(Instant.now().plusSeconds(30L * 24 * 60 * 60));

        log.info("Sending message from {} to {}",
                message.getSender(),
                message.getReceiver());

        Message saved = repository.save(message);

        log.info("Message {} stored successfully",
                saved.getId());

        MessageResponse response =
                new MessageResponse(
                        saved.getId(),
                        saved.getSender(),
                        saved.getMessage(),
                        saved.getCreatedAt()
                );

        messagingTemplate.convertAndSend(
                "/topic/" + message.getReceiver(),
                response
        );

        messagingTemplate.convertAndSend(
                "/topic/" + message.getSender(),
                response
        );
    }

    public List<MessageResponse> getMessages(String receiver) {

        properties.validateUser(receiver);

        log.info("Loading messages for {}", receiver);

        return repository
                .findByReceiverAndExpiresAtAfterOrderByCreatedAtAsc(
                        receiver,
                        Instant.now())
                .stream()
                .map(message -> new MessageResponse(
                        message.getId(),
                        message.getSender(),
                        message.getMessage(),
                        message.getCreatedAt()
                ))
                .toList();
    }

    public List<MessageResponse> getConversation(String userKey) {

        log.info("Loading conversation for {}", userKey);

        String sender = properties.sender(userKey);
        String receiver = properties.receiver(userKey);

        return repository
                .findBySenderAndReceiverOrSenderAndReceiverOrderByCreatedAtAsc(
                        sender,
                        receiver,
                        receiver,
                        sender
                )
                .stream()
                .map(message -> new MessageResponse(
                        message.getId(),
                        message.getSender(),
                        message.getMessage(),
                        message.getCreatedAt()
                ))
                .toList();
    }

    @Transactional
    public List<MessageResponse> getUnreadMessages(String receiver) {

        log.info("Loading unread messages for {}", receiver);

        List<MessageResponse> messages =
                repository
                        .findByReceiverAndIsReadFalseAndExpiresAtAfterOrderByCreatedAtAsc(
                                receiver,
                                Instant.now())
                        .stream()
                        .map(message -> new MessageResponse(
                                message.getId(),
                                message.getSender(),
                                message.getMessage(),
                                message.getCreatedAt()
                        ))
                        .toList();

        repository.markAsRead(receiver);

        return messages;
    }
}