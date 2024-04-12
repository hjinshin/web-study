package com.example.chatting.api.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ChatRoomListReadResponse(List<ChatRoomItemResponse> messageList, boolean hasNext) {
}
