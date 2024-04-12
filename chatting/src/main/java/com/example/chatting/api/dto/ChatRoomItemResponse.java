package com.example.chatting.api.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChatRoomItemResponse(Long roomId, LocalDateTime createdAt) {
}
