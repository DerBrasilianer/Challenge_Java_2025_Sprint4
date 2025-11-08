package com.fiap.gestaofrota.controller;

import com.fiap.gestaofrota.dto.PatioDTO;
import com.fiap.gestaofrota.entity.PatioEntity;
import com.fiap.gestaofrota.mapper.PatioMapper;
import com.fiap.gestaofrota.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patios")
public class PatioController {
    private final PatioService patioService;

    public PatioController(PatioService patioService) {
        this.patioService = patioService;
    }

    @GetMapping
    public List<PatioDTO> listar() {
        return patioService.listarTodos().stream().map(PatioMapper::toPatioDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatioDTO> buscarPorId(@PathVariable Long id) {
        PatioEntity entity = patioService.buscarPorId(id);
        return ResponseEntity.ok(PatioMapper.toPatioDTO(entity));
    }

    @PostMapping
    public ResponseEntity<PatioDTO> criar(@RequestBody @Valid PatioDTO patioDto) {
        PatioEntity entity = PatioMapper.toPatioEntity(patioDto);
        PatioEntity salvo = patioService.criar(entity);
        return ResponseEntity.ok(PatioMapper.toPatioDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatioDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PatioDTO patioDto) {
        PatioEntity entity = PatioMapper.toPatioEntity(patioDto);
        PatioEntity atualizado = patioService.atualizar(id, entity);
        return ResponseEntity.ok(PatioMapper.toPatioDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        patioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
