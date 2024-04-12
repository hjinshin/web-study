package com.example.chatting.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatMessageId;
    private String content;
    private String writer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoomJpaEntity chatRoom;
}
