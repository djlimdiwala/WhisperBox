package com.whisperbox.scheduler;

import com.whisperbox.repository.MessageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageCleanupScheduler {

    private final MessageRepository repository;

    public MessageCleanupScheduler(MessageRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRate = 60000)
    public void cleanupExpiredMessages() {

        int deleted = repository.deleteExpiredMessages();

        System.out.println(
                "Cleanup Job: Deleted "
                        + deleted
                        + " expired messages."
        );
    }
}