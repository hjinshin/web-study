package com.example.chatting.application.service;

import com.example.chatting.api.dto.ChatMessageResponse;
import com.example.chatting.application.usecase.ChangeChatRoomUseCase;
import com.example.chatting.core.annotation.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@UseCase
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ChangeChatRoomService implements ChangeChatRoomUseCase {

    private final SimpMessagingTemplate messagingTemplate;
    private Long currentRoomId;

    @Override
    public Long getCurrentRoomId() {
        return currentRoomId;
    }
    @Override
    public void setCurrentRoomId(Long roomId) {
        this.currentRoomId = roomId;
    }
    @Override
    public boolean shouldMoveToRoom(Long newRoomId) {
        return !newRoomId.equals(currentRoomId);
    }
    @Override
    public void changeChatRoom(Long newRoomId) {
        if(shouldMoveToRoom(newRoomId)) {
            sendCloseRoomMessageToClient(currentRoomId);
            sendChangeRoomMessageToClient(currentRoomId, newRoomId);
            sendSetRoomMessageToClient(newRoomId);
            setCurrentRoomId(newRoomId);
        }
    }
    private void sendSetRoomMessageToClient(Long newRoomId) {
        messagingTemplate.convertAndSend(
                "/topic/public/" + 0,
                ChatMessageResponse.builder()
                        .type("Move")
                        .id(newRoomId)
                        .build());
    }

    private void sendChangeRoomMessageToClient(Long currentRoomId, Long newRoomId) {
        messagingTemplate.convertAndSend(
                "/topic/public/" + currentRoomId,
                ChatMessageResponse.builder()
                        .type("Move")
                        .id(newRoomId)
                        .build());
    }

    private void sendCloseRoomMessageToClient(Long currentRoomId) {
        messagingTemplate.convertAndSend(
                "/topic/public/" + currentRoomId,
                ChatMessageResponse.builder()
                        .type("Close")
                        .content("연결이 종료되었습니다.")
                        .build());
    }
}
