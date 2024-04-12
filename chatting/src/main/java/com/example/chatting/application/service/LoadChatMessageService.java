package com.example.chatting.application.service;

import com.example.chatting.api.dto.ChatMessageResponse;
import com.example.chatting.application.port.out.LoadChatMessagePort;
import com.example.chatting.application.usecase.ChatMessageLoadUseCase;
import com.example.chatting.core.annotation.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
@Transactional
public class LoadChatMessageService implements ChatMessageLoadUseCase {
    private final LoadChatMessagePort loadChatMessagePort;

    @Override
    public List<ChatMessageResponse> getChatMessageList(Long roomId) {
        return loadChatMessagePort.loadChatMessageList(roomId)
                .stream().map(chatMessage ->
                        ChatMessageResponse.builder()
                                .id(chatMessage.getChatId().value())
                                .writer(chatMessage.getWriter())
                                .content(chatMessage.getContent())
                                .build())
                .collect(Collectors.toList());
    }
}
