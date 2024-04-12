package com.example.chatting.infrastructure.adapter;

import com.example.chatting.application.port.out.LoadChatRoomPort;
import com.example.chatting.core.annotation.PersistenceAdapter;
import com.example.chatting.domain.model.ChatRoom;
import com.example.chatting.domain.model.ChatRoomList;
import com.example.chatting.infrastructure.adapter.persistence.entity.ChatRoomJpaEntity;
import com.example.chatting.infrastructure.adapter.persistence.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class ChatRoomLoadPersistenceAdapter implements LoadChatRoomPort {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public ChatRoomList search(PageRequest pageRequest) {
        Slice<ChatRoomJpaEntity> chatRoomJpaEntityList = chatRoomRepository.findAllBy(pageRequest);
        List<ChatRoom> chatRoomList = chatRoomJpaEntityList.stream()
                .map(chatRoomJpaEntity -> ChatRoom.builder()
                        .roomId(new ChatRoom.RoomId(chatRoomJpaEntity.getChatRoomId()))
                        .createdAt(chatRoomJpaEntity.getCreatedAt())
                        .build()).toList();
        boolean hasNext = chatRoomJpaEntityList.hasNext();
        return ChatRoomList.builder()
                .chatRoomList(chatRoomList)
                .hasNext(hasNext)
                .build();
    }
}
