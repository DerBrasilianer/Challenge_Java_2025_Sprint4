package com.fiap.gestaofrota.service;

import com.fiap.gestaofrota.entity.MotoEntity;
import com.fiap.gestaofrota.enums.Marcas;
import com.fiap.gestaofrota.enums.Modelos;
import com.fiap.gestaofrota.repository.MotoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MotoServiceTest {

    @Mock
    private MotoRepository motoRepository;

    @InjectMocks
    private MotoService motoService;

    private MotoEntity moto;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);

        moto = new MotoEntity();
        moto.setId(1L);
        moto.setMarca(Marcas.HONDA);
        moto.setModelo(Modelos.POP);
        moto.setPlaca("ABC-1234");
        moto.setAno(2023);
        moto.setNumeroIot(100L);
        moto.setPatio(null); // deixamos null, pois o teste não depende disso

    }

    @Test
    void deveListarMotosSemFiltro() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<MotoEntity> page = new PageImpl<>(Collections.singletonList(moto));
        when(motoRepository.findAll(pageable)).thenReturn(page);

        Page<MotoEntity> resultado = motoService.listar(pageable, null);

        assertEquals(1, resultado.getTotalElements());
        verify(motoRepository, times(1)).findAll(pageable);

    }

    @Test
    void deveListarMotosComFiltroDePlaca() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<MotoEntity> page = new PageImpl<>(Collections.singletonList(moto));
        when(motoRepository.findByPlacaContainingIgnoreCase("ABC", pageable)).thenReturn(page);

        Page<MotoEntity> resultado = motoService.listar(pageable, "ABC");

        assertEquals(1, resultado.getTotalElements());
        verify(motoRepository, times(1)).findByPlacaContainingIgnoreCase("ABC", pageable);

    }

    @Test
    void deveBuscarMotoPorId() {

        when(motoRepository.findById(1L)).thenReturn(Optional.of(moto));

        MotoEntity encontrada = motoService.buscarPorId(1L);

        assertNotNull(encontrada);
        assertEquals(Marcas.HONDA, encontrada.getMarca());

    }

    @Test
    void deveLancarExcecaoAoBuscarMotoInexistente() {

        when(motoRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> motoService.buscarPorId(2L));

    }

    @Test
    void deveCriarMoto() {

        when(motoRepository.save(any(MotoEntity.class))).thenReturn(moto);

        MotoEntity criada = motoService.criar(moto);

        assertNotNull(criada);
        assertEquals(Marcas.HONDA, criada.getMarca());
        verify(motoRepository, times(1)).save(moto);

    }

    @Test
    void deveAtualizarMoto() {

        when(motoRepository.findById(1L)).thenReturn(Optional.of(moto));
        when(motoRepository.save(any(MotoEntity.class))).thenReturn(moto);

        MotoEntity atualizada = new MotoEntity();
        atualizada.setMarca(Marcas.VMOTO);
        atualizada.setModelo(Modelos.SPORT);
        atualizada.setPlaca("XYZ-9999");
        atualizada.setAno(2024);
        atualizada.setNumeroIot(200L);

        MotoEntity resultado = motoService.atualizar(1L, atualizada);

        assertEquals(Marcas.VMOTO, resultado.getMarca());
        assertEquals(Modelos.SPORT, resultado.getModelo());
        assertEquals(200L, resultado.getNumeroIot());
        verify(motoRepository, times(1)).save(moto);

    }

    @Test
    void deveDeletarMoto() {

        doNothing().when(motoRepository).deleteById(1L);

        motoService.deletar(1L);

        verify(motoRepository, times(1)).deleteById(1L);

    }

}
