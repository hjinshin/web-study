package com.example.chatting.application.service;

import com.example.chatting.application.port.in.command.ChatMessageCreateCommand;
import com.example.chatting.application.port.out.CreateChatMessagePort;
import com.example.chatting.application.usecase.ChatMessageCreateUseCase;
import com.example.chatting.core.annotation.UseCase;
import com.example.chatting.domain.model.ChatMessage;
import com.example.chatting.domain.model.ChatRoom;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class CreateChatMessageService implements ChatMessageCreateUseCase {
    private final CreateChatMessagePort createChatMessagePort;
    @Override
    public Long createChatMessage(ChatMessageCreateCommand command) {
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoomId(new ChatRoom.RoomId(command.roomId()))
                .content(command.content())
                .writer(command.from())
                .build();
        return createChatMessagePort.createChatMessage(chatMessage);
    }
}
