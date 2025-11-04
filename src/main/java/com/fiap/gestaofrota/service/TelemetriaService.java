package com.fiap.gestaofrota.service;

import com.fiap.gestaofrota.entity.TelemetriaEntity;
import com.fiap.gestaofrota.repository.TelemetriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelemetriaService {

    private final TelemetriaRepository repository;

    public TelemetriaEntity salvar(TelemetriaEntity t) {
        return repository.save(t);
    }

    public List<TelemetriaEntity> listarTodos() {
        return repository.findTop100ByOrderByIdDesc();
    }

    public List<TelemetriaEntity> listarPorDispositivo(String dispositivo) {
        return repository.findTop50ByDispositivoOrderByIdDesc(dispositivo);
    }

}
