package com.whisperbox.service;

import com.whisperbox.config.WhisperBoxProperties;
import com.whisperbox.dto.SendMessageRequest;
import com.whisperbox.entity.Message;
import com.whisperbox.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepository repository;

    @Mock
    private WhisperBoxProperties properties;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private MessageService service;

    @Test
    void shouldSendMessage() {

        SendMessageRequest request = new SendMessageRequest();
        request.setMessage("Hello");

        when(properties.sender("A")).thenReturn("A");
        when(properties.receiver("A")).thenReturn("B");

        when(repository.save(any(Message.class)))
                .thenAnswer(invocation -> {

                    Message message = invocation.getArgument(0);

                    message.setId(1L);

                    return message;
                });

        service.send("A", request);

        ArgumentCaptor<Message> captor =
                ArgumentCaptor.forClass(Message.class);

        verify(repository).save(captor.capture());

        Message saved = captor.getValue();

        assertEquals("A", saved.getSender());
        assertEquals("B", saved.getReceiver());
        assertEquals("Hello", saved.getMessage());

        assertNotNull(saved.getCreatedAt());
        assertNotNull(saved.getExpiresAt());
    }
}