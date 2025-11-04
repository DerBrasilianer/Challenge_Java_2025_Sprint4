package com.fiap.gestaofrota.repository;

import com.fiap.gestaofrota.entity.MotoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<MotoEntity, Long> {

    Page<MotoEntity> findByPlacaContainingIgnoreCase(String placa, Pageable pageable);

}
