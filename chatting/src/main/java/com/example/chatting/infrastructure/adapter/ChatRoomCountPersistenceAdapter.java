package com.example.chatting.infrastructure.adapter;

import com.example.chatting.application.port.out.CountChatRoomPort;
import com.example.chatting.core.annotation.PersistenceAdapter;
import com.example.chatting.infrastructure.adapter.persistence.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@PersistenceAdapter
@RequiredArgsConstructor
public class ChatRoomCountPersistenceAdapter implements CountChatRoomPort {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public Long countTotalNumOfRooms() {
        return chatRoomRepository.count();
    }

    @Override
    public Long countTodayNumOfRooms() {
        LocalDateTime today = LocalDate.now().atTime(LocalTime.MIN);
        LocalDateTime tomorrow = LocalDate.now().atTime(LocalTime.MAX);
        return chatRoomRepository.countByCreatedAtBetween(today, tomorrow);
    }
}
