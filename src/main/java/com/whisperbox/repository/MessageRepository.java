package com.whisperbox.repository;

import com.whisperbox.entity.Message;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByReceiverOrderByCreatedAtAsc(String receiver);
}