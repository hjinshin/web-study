package com.example.chatting.application.port.in.query;

import lombok.Builder;

@Builder
public record ChatRoomListQuery(int page, int size) {
}
