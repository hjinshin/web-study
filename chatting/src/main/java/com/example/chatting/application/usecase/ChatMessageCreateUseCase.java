package com.example.chatting.application.usecase;

import com.example.chatting.application.port.in.command.ChatMessageCreateCommand;

public interface ChatMessageCreateUseCase {
    Long createChatMessage(ChatMessageCreateCommand command);
}
