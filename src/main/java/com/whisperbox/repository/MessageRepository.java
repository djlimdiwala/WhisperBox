package com.whisperbox.repository;

import com.whisperbox.entity.Message;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import java.time.LocalDateTime;
import org.springframework.data.jdbc.repository.query.Modifying;

public interface MessageRepository extends CrudRepository<Message, Long> {

List<Message> findByReceiverAndExpiresAtAfterOrderByCreatedAtAsc(
        String receiver,
        LocalDateTime now);
List<Message> findBySenderAndReceiverOrderByCreatedAtAsc(
        String sender,
        String receiver
        );

        List<Message> findBySenderAndReceiverOrSenderAndReceiverOrderByCreatedAtAsc(
                String sender1,
                String receiver1,
                String sender2,
                String receiver2
        );
        List<Message> findByReceiverAndIsReadFalseAndExpiresAtAfterOrderByCreatedAtAsc(
                String receiver,
                LocalDateTime now);

        @Modifying
        @Query("""
        UPDATE messages
        SET is_read = TRUE
        WHERE receiver = :receiver
        AND is_read = FALSE
        """)
        void markAsRead(String receiver);


        @Modifying
        @Query("""
        DELETE FROM messages
        WHERE expires_at < NOW()
        """)
        int deleteExpiredMessages();
}