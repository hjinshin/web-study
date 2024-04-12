package com.example.chatting.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ChatRoom {
    private RoomId roomId;
    private LocalDateTime createdAt;
    private List<ChatMessage> messageList;
    public record RoomId(Long value) {
    }
}