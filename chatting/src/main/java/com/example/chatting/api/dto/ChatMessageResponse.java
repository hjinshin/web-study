package com.example.chatting.api.dto;

import lombok.Builder;

@Builder
public record ChatMessageResponse(String type, Long id, String content, String writer) {
}
