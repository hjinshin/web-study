package com.example.chatting.application.port.out;

import com.example.chatting.domain.model.ChatMessage;

public interface CreateChatMessagePort {
    Long createChatMessage(ChatMessage chatMessage);
}
