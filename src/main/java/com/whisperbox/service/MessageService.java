package com.whisperbox.service;

import com.whisperbox.config.WhisperBoxProperties;
import com.whisperbox.dto.SendMessageRequest;
import com.whisperbox.entity.Message;
import com.whisperbox.repository.MessageRepository;
import org.springframework.stereotype.Service;
import com.whisperbox.dto.MessageResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

import java.time.LocalDateTime;

@Service
public class MessageService {

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

        message.setCreatedAt(LocalDateTime.now());

        message.setExpiresAt(LocalDateTime.now().plusDays(30));

        Message saved = repository.save(message);

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

        return repository
                .findByReceiverAndExpiresAtAfterOrderByCreatedAtAsc(
                        receiver,
                        LocalDateTime.now())
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
    List<MessageResponse> messages =
                repository.findByReceiverAndIsReadFalseAndExpiresAtAfterOrderByCreatedAtAsc(
                        receiver,
                        LocalDateTime.now())
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