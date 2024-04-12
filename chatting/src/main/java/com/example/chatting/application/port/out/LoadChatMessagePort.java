package com.example.chatting.application.port.out;

import com.example.chatting.domain.model.ChatMessage;

import java.util.List;

public interface LoadChatMessagePort {
    List<ChatMessage> loadChatMessageList(Long roomId);
}
