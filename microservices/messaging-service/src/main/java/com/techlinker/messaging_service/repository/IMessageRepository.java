package com.techlinker.messaging_service.repository;

import com.techlinker.messaging_service.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessageRepository extends JpaRepository<Message, Long> {
}
