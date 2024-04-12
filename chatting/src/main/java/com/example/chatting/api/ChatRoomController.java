package com.example.chatting.api;

import com.example.chatting.api.dto.ChatRoomListReadResponse;
import com.example.chatting.application.port.in.command.ChatRoomCreateCommand;
import com.example.chatting.application.port.in.query.ChatRoomListQuery;
import com.example.chatting.application.usecase.ChangeChatRoomUseCase;
import com.example.chatting.application.usecase.ChatRoomCountUseCase;
import com.example.chatting.application.usecase.ChatRoomCreateUseCase;
import com.example.chatting.application.usecase.ChatRoomLoadUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
@Log4j2
public class ChatRoomController {
    private final ChatRoomCreateUseCase chatRoomCreateUseCase;
    private final ChatRoomCountUseCase chatRoomCountUseCase;
    private final ChangeChatRoomUseCase changeChatRoomUseCase;
    private final ChatRoomLoadUseCase chatRoomLoadUseCase;

    @PostMapping
    public ResponseEntity<Long> createRoom() {
        ChatRoomCreateCommand chatRoomCreateCommand = ChatRoomCreateCommand.builder()
                .build();
        Long createdRoomId = chatRoomCreateUseCase.createChatRoom(chatRoomCreateCommand);

        changeChatRoomUseCase.changeChatRoom(createdRoomId);
        log.info("createdRoomId: {}", createdRoomId);
        return ResponseEntity.ok(createdRoomId);
    }

    @GetMapping
    public ResponseEntity<ChatRoomListReadResponse> getChatRoomList(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size) {
        ChatRoomListQuery chatRoomListQuery = ChatRoomListQuery.builder()
                .page(page)
                .size(size)
                .build();
        return ResponseEntity.ok(chatRoomLoadUseCase.getChatRoomList(chatRoomListQuery));
    }

    @GetMapping("count")
    public ResponseEntity<Map<String, Long>> countRooms() {
        Long totalRooms = chatRoomCountUseCase.countTotalNumOfRooms();
        Long todayRooms = chatRoomCountUseCase.countTodayNumOfRooms();
        return ResponseEntity.ok(Map.of(
                "total", totalRooms,
                "today", todayRooms
        ));
    }
}
