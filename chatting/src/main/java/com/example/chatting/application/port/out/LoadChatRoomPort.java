package com.example.chatting.application.port.out;

import com.example.chatting.domain.model.ChatRoomList;
import org.springframework.data.domain.PageRequest;

public interface LoadChatRoomPort {
    ChatRoomList search(PageRequest pageRequest);

}
