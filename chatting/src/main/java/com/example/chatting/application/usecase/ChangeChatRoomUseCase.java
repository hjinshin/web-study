package com.example.chatting.application.usecase;

public interface ChangeChatRoomUseCase {
    Long getCurrentRoomId();
    void setCurrentRoomId(Long roomId);
    boolean shouldMoveToRoom(Long newRoomId);
    void changeChatRoom(Long newRoomId);
}
