package com.fiap.gestaofrota.repository;

import com.fiap.gestaofrota.entity.PushTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PushTokenRepository extends JpaRepository<PushTokenEntity, Long> {

    Optional<PushTokenEntity> findByToken(String token);

}
