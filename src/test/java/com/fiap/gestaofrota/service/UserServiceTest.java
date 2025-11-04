package com.fiap.gestaofrota.service;

import com.fiap.gestaofrota.dto.UserDTO;
import com.fiap.gestaofrota.entity.UserEntity;
import com.fiap.gestaofrota.enums.Role;
import com.fiap.gestaofrota.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private UserDTO userDTO;
    private UserEntity userEntity;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);

        userDTO = new UserDTO();
        userDTO.setUsername("admin");
        userDTO.setPassword("123456");
        userDTO.setRole(Role.ADMIN);

        userEntity = UserEntity.builder()
                .id(1L)
                .username("admin")
                .password("encodedPassword")
                .role(Role.ADMIN)
                .build();
    }

    @Test
    void deveRetornarTrueQuandoUsuarioExistir() {

        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(userEntity));

        boolean existe = userService.existsByUsername("admin");

        assertTrue(existe);
        verify(userRepository, times(1)).findByUsername("admin");

    }

    @Test
    void deveRetornarFalseQuandoUsuarioNaoExistir() {

        when(userRepository.findByUsername("user")).thenReturn(Optional.empty());

        boolean existe = userService.existsByUsername("user");

        assertFalse(existe);
        verify(userRepository, times(1)).findByUsername("user");

    }

    @Test
    void deveRegistrarNovoUsuarioComRolePadraoUserQuandoNaoInformada() {

        userDTO.setRole(null);
        when(passwordEncoder.encode("123456")).thenReturn("encodedPassword");
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> {
            UserEntity u = invocation.getArgument(0);
            u.setId(1L);
            return u;
        });

        UserEntity salvo = userService.registerNewUser(userDTO);

        assertNotNull(salvo);
        assertEquals("admin", salvo.getUsername());
        assertEquals("encodedPassword", salvo.getPassword());
        assertEquals(Role.USER, salvo.getRole());
        verify(passwordEncoder, times(1)).encode("123456");
        verify(userRepository, times(1)).save(any(UserEntity.class));

    }

    @Test
    void deveRegistrarNovoUsuarioComRoleInformada() {

        when(passwordEncoder.encode("123456")).thenReturn("encodedPassword");
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserEntity salvo = userService.registerNewUser(userDTO);

        assertNotNull(salvo);
        assertEquals("admin", salvo.getUsername());
        assertEquals(Role.ADMIN, salvo.getRole());
        assertEquals("encodedPassword", salvo.getPassword());
        verify(passwordEncoder, times(1)).encode("123456");
        verify(userRepository, times(1)).save(any(UserEntity.class));

    }

}
