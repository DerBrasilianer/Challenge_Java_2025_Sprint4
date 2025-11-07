package com.fiap.gestaofrota.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "CH_TB_PUSHTOKEN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PushTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 512)
    private String token;

    @Column(length = 128)
    private String platform;

    @Column(length = 512)
    private String meta;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

}
