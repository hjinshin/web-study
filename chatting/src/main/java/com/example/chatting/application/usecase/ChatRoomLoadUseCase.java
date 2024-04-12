package com.example.chatting.application.usecase;

import com.example.chatting.api.dto.ChatRoomListReadResponse;
import com.example.chatting.application.port.in.query.ChatRoomListQuery;

public interface ChatRoomLoadUseCase {
    ChatRoomListReadResponse getChatRoomList(ChatRoomListQuery query);
}
