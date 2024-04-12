package com.example.chatting.application.port.in.command;

import lombok.Builder;

@Builder
public record ChatMessageCreateCommand(Long roomId, String content, String from) {
}