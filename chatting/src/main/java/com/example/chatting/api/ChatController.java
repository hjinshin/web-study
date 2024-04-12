package com.example.chatting.api;

import com.example.chatting.api.dto.ChatMessageRequest;
import com.example.chatting.api.dto.ChatMessageResponse;
import com.example.chatting.application.port.in.command.ChatMessageCreateCommand;
import com.example.chatting.application.usecase.ChatMessageCreateUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ChatController {
    private final ChatMessageCreateUseCase chatMessageCreateUseCase;
    @MessageMapping({"/chat/customer/{roomId}/send", "/chat/counselor/{roomId}/send"})
    @SendTo("/topic/public/{roomId}")
    public ChatMessageResponse sendMessage(@DestinationVariable Long roomId, @Payload ChatMessageRequest chatMessage) {
        log.info("message: {}", chatMessage);
        ChatMessageCreateCommand chatMessageCreateCommand = ChatMessageCreateCommand.builder()
                .content(chatMessage.text())
                .from(chatMessage.from())
                .roomId(roomId)
                .build();
        Long chatId = chatMessageCreateUseCase.createChatMessage(chatMessageCreateCommand);
        return ChatMessageResponse.builder()
                .type("Chat")
                .id(chatId)
                .content(chatMessage.text())
                .writer(chatMessage.from())
                .build();
    }
}
