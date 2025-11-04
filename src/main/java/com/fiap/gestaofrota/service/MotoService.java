package com.fiap.gestaofrota.service;

import com.fiap.gestaofrota.entity.MotoEntity;
import com.fiap.gestaofrota.repository.MotoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MotoService {

    private final MotoRepository repository;

    public MotoService(MotoRepository repository) {

        this.repository = repository;

    }

    public Page<MotoEntity> listar(Pageable pageable, String placa) {

        if (placa != null && !placa.isBlank()) {

            return repository.findByPlacaContainingIgnoreCase(placa, pageable);

        }

        return repository.findAll(pageable);

    }

    public MotoEntity buscarPorId(Long id) {

        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Moto com id " + id + " não encontrada"));

    }

    public MotoEntity criar(MotoEntity moto) {

        return repository.save(moto);

    }

    public MotoEntity atualizar(Long id, MotoEntity motoAtualizada) {

        MotoEntity existente = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Moto com id " + id + " não encontrada"));
        existente.setMarca(motoAtualizada.getMarca());
        existente.setModelo(motoAtualizada.getModelo());
        existente.setPlaca(motoAtualizada.getPlaca());
        existente.setAno(motoAtualizada.getAno());
        existente.setNumeroIot(motoAtualizada.getNumeroIot());
        existente.setPatio(motoAtualizada.getPatio());

        return repository.save(existente);

    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
