package com.example.chatting.application.usecase;

import com.example.chatting.api.dto.ChatMessageResponse;

import java.util.List;

public interface ChatMessageLoadUseCase {
    List<ChatMessageResponse> getChatMessageList(Long roomId);
}
