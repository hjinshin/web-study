package com.example.chatting.infrastructure.adapter.persistence.repository;

import com.example.chatting.infrastructure.adapter.persistence.entity.ChatMessageJpaEntity;
import com.example.chatting.infrastructure.adapter.persistence.entity.ChatRoomJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageJpaEntity, Long> {
    List<ChatMessageJpaEntity> findAllByChatRoom(ChatRoomJpaEntity chatRoomJpaEntity);
}
