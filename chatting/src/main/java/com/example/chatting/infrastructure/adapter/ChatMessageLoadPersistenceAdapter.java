package com.example.chatting.infrastructure.adapter;

import com.example.chatting.application.port.out.LoadChatMessagePort;
import com.example.chatting.core.annotation.PersistenceAdapter;
import com.example.chatting.domain.model.ChatMessage;
import com.example.chatting.infrastructure.adapter.persistence.entity.ChatMessageJpaEntity;
import com.example.chatting.infrastructure.adapter.persistence.entity.ChatRoomJpaEntity;
import com.example.chatting.infrastructure.adapter.persistence.repository.ChatMessageRepository;
import com.example.chatting.infrastructure.adapter.persistence.repository.ChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class ChatMessageLoadPersistenceAdapter implements LoadChatMessagePort {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    @Transactional
    @Override
    public List<ChatMessage> loadChatMessageList(Long roomId) {
        ChatRoomJpaEntity chatRoomJpaEntity = chatRoomRepository.findByChatRoomId(roomId)
                .orElseThrow(RuntimeException::new);
        List<ChatMessageJpaEntity> chatMessageJpaEntities = chatMessageRepository.findAllByChatRoom(chatRoomJpaEntity);
        return chatMessageJpaEntities.stream().map(
                chatMessageJpaEntity -> ChatMessage.builder()
                        .chatId(new ChatMessage.ChatId(chatMessageJpaEntity.getChatMessageId()))
                        .writer(chatMessageJpaEntity.getWriter())
                        .content(chatMessageJpaEntity.getContent())
                        .build()
        ).collect(Collectors.toList());
    }
}
