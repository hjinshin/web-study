package com.example.chatting.application.usecase;

import com.example.chatting.application.port.in.command.ChatRoomCreateCommand;

public interface ChatRoomCreateUseCase {
    Long createChatRoom(ChatRoomCreateCommand command);
}
