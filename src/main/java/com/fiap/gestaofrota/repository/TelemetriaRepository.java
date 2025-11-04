package com.fiap.gestaofrota.repository;

import com.fiap.gestaofrota.entity.TelemetriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelemetriaRepository extends JpaRepository<TelemetriaEntity, Long> {

    List<TelemetriaEntity> findTop100ByOrderByIdDesc();
    List<TelemetriaEntity> findTop50ByDispositivoOrderByIdDesc(String dispositivo);

}