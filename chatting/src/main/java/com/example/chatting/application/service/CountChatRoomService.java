package com.example.chatting.application.service;

import com.example.chatting.application.port.out.CountChatRoomPort;
import com.example.chatting.application.usecase.ChatRoomCountUseCase;
import com.example.chatting.core.annotation.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class CountChatRoomService implements ChatRoomCountUseCase {
    private final CountChatRoomPort countChatRoomPort;
    @Override
    public Long countTotalNumOfRooms() {
        return countChatRoomPort.countTotalNumOfRooms();
    }

    @Override
    public Long countTodayNumOfRooms() {
        return countChatRoomPort.countTodayNumOfRooms();
    }
}
