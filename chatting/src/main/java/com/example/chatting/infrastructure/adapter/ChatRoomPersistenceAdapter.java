package com.example.chatting.infrastructure.adapter;

import com.example.chatting.application.port.out.CreateChatRoomPort;
import com.example.chatting.core.annotation.PersistenceAdapter;
import com.example.chatting.domain.model.ChatRoom;
import com.example.chatting.infrastructure.adapter.persistence.entity.ChatRoomJpaEntity;
import com.example.chatting.infrastructure.adapter.persistence.repository.ChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@PersistenceAdapter
@RequiredArgsConstructor
public class ChatRoomPersistenceAdapter implements CreateChatRoomPort {
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    @Override
    public Long createChatRoom(ChatRoom chatRoom) {
        ChatRoomJpaEntity chatRoomJpaEntity = ChatRoomJpaEntity.builder()
                .createdAt(LocalDateTime.now())
                .build();
        ChatRoomJpaEntity savedEntity = chatRoomRepository.save(chatRoomJpaEntity);
        return savedEntity.getChatRoomId();
    }
}
