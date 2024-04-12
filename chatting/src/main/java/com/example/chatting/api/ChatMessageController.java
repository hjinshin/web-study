package com.example.chatting.api;

import com.example.chatting.api.dto.ChatMessageResponse;
import com.example.chatting.application.usecase.ChatMessageLoadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class ChatMessageController {
    private final ChatMessageLoadUseCase chatMessageLoadUseCase;

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<ChatMessageResponse>> getMessageList(@PathVariable Long roomId) {
        return ResponseEntity.ok(chatMessageLoadUseCase.getChatMessageList(roomId));
    }
}
