package com.example.chatting.infrastructure.adapter.persistence.repository;

import com.example.chatting.infrastructure.adapter.persistence.entity.ChatRoomJpaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoomJpaEntity, Long> {
    Optional<ChatRoomJpaEntity> findByChatRoomId(Long id);

    @Query("select count(room) From ChatRoomJpaEntity room where room.createdAt >= :startDate AND room.createdAt < :endDate")
    long countByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    Slice<ChatRoomJpaEntity> findAllBy(Pageable pageable);

}
