package com.example.chatting.application.service;

import com.example.chatting.api.dto.ChatRoomItemResponse;
import com.example.chatting.api.dto.ChatRoomListReadResponse;
import com.example.chatting.application.port.in.query.ChatRoomListQuery;
import com.example.chatting.application.port.out.LoadChatRoomPort;
import com.example.chatting.application.usecase.ChatRoomLoadUseCase;
import com.example.chatting.core.annotation.UseCase;
import com.example.chatting.domain.model.ChatRoom;
import com.example.chatting.domain.model.ChatRoomList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class LoadChatRoomService implements ChatRoomLoadUseCase {
    private final LoadChatRoomPort loadChatRoomPort;

    @Override
    public ChatRoomListReadResponse getChatRoomList(ChatRoomListQuery query) {
        PageRequest pageRequest = PageRequest.of(query.page(), query.size(), Sort.by("chatRoomId").descending());
        ChatRoomList chatRoomList = loadChatRoomPort.search(pageRequest);
        List<ChatRoom> itemRes = chatRoomList.getChatRoomList();

        return ChatRoomListReadResponse.builder()
                .messageList(itemRes.stream()
                        .map(chatRoom -> ChatRoomItemResponse.builder()
                                .roomId(chatRoom.getRoomId().value())
                                .createdAt(chatRoom.getCreatedAt())
                                .build())
                        .collect(Collectors.toList()))
                .hasNext(chatRoomList.isHasNext())
                .build();
    }
}
