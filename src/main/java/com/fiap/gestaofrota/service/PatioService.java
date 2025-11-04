package com.fiap.gestaofrota.service;

import com.fiap.gestaofrota.entity.PatioEntity;
import com.fiap.gestaofrota.repository.PatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatioService {
    private final PatioRepository repository;

    public PatioService(PatioRepository repository) {

        this.repository = repository;

    }

    public List<PatioEntity> listarTodos() {

        return repository.findAll();

    }

    public PatioEntity buscarPorId(Long id) {

        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pátio com id " + id + " não encontrado"));

    }

    public PatioEntity criar(PatioEntity patio) {

        return repository.save(patio);

    }

    public PatioEntity atualizar(Long id, PatioEntity atualizado) {

        PatioEntity existente = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pátio com id " + id + " não encontrado"));
        existente.setNome(atualizado.getNome());
        existente.setEndereco(atualizado.getEndereco());
        existente.setTelefone(atualizado.getTelefone());

        return repository.save(existente);

    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
