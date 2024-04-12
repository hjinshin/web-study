package com.example.chatting.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ChatRoomList {
    private List<ChatRoom> chatRoomList;
    private boolean hasNext;
}
