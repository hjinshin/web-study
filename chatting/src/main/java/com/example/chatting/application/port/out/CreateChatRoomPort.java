package com.example.chatting.application.port.out;

import com.example.chatting.domain.model.ChatRoom;

public interface CreateChatRoomPort {
    Long createChatRoom(ChatRoom chatRoom);
}
