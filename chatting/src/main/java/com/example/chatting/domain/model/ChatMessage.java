package com.example.chatting.domain.model;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ChatMessage{
    private ChatId chatId;
    private String writer;
    private String content;
    private ChatRoom.RoomId chatRoomId;
    public record ChatId(Long value) {
    }
}