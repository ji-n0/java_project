package com.example.bybitAutoTrade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "mbr")
public class Mbr {

    @Id
    @Column(name = "id", length = 50, nullable = false)
    private String id;  // 회원 ID (UUID 또는 사용자 지정 ID)

    @Column(name = "password", length = 255, nullable = false)
    private String password;  // 암호화된 비밀번호

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;  // 등록일자 (자동 생성)

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;  // 수정일자 (자동 업데이트)
}
