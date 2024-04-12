package com.example.chatting.application.service;

import com.example.chatting.application.port.in.command.ChatRoomCreateCommand;
import com.example.chatting.application.port.out.CreateChatRoomPort;
import com.example.chatting.application.usecase.ChatRoomCreateUseCase;
import com.example.chatting.core.annotation.UseCase;
import com.example.chatting.domain.model.ChatRoom;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class CreateChatRoomService implements ChatRoomCreateUseCase {
    private final CreateChatRoomPort createChatRoomPort;
    @Override
    public Long createChatRoom(ChatRoomCreateCommand command) {
        ChatRoom chatRoom = ChatRoom.builder()
                .build();
        return createChatRoomPort.createChatRoom(chatRoom);
    }
}
