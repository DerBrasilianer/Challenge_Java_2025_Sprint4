package com.fiap.gestaofrota.entity;

import com.fiap.gestaofrota.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CH_TB_USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
