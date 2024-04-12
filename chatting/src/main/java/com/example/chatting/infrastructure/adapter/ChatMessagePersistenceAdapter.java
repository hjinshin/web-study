package com.example.chatting.infrastructure.adapter;

import com.example.chatting.application.port.out.CreateChatMessagePort;
import com.example.chatting.core.annotation.PersistenceAdapter;
import com.example.chatting.domain.model.ChatMessage;
import com.example.chatting.infrastructure.adapter.persistence.entity.ChatMessageJpaEntity;
import com.example.chatting.infrastructure.adapter.persistence.entity.ChatRoomJpaEntity;
import com.example.chatting.infrastructure.adapter.persistence.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ChatMessagePersistenceAdapter implements CreateChatMessagePort {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public Long createChatMessage(ChatMessage chatMessage) {
        ChatRoomJpaEntity chatRoomJpaEntity = chatRoomRepository.findById(chatMessage.getChatRoomId().value())
                .orElseThrow(RuntimeException::new);
        ChatMessageJpaEntity chatMessageJpaEntity = ChatMessageJpaEntity.builder()
                .chatRoom(chatRoomJpaEntity)
                .content(chatMessage.getContent())
                .writer(chatMessage.getWriter())
                .build();
        chatRoomJpaEntity.createMessage(chatMessageJpaEntity);
        chatRoomRepository.save(chatRoomJpaEntity);
        return chatMessageJpaEntity.getChatMessageId();
    }
}
