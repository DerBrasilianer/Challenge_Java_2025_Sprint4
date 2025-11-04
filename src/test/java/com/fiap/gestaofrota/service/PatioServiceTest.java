package com.fiap.gestaofrota.service;

import com.fiap.gestaofrota.entity.PatioEntity;
import com.fiap.gestaofrota.repository.PatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatioServiceTest {

    @Mock
    private PatioRepository patioRepository;

    @InjectMocks
    private PatioService patioService;

    private PatioEntity patio;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);

        patio = new PatioEntity();
        patio.setId(1L);
        patio.setNome("Pátio Central");
        patio.setEndereco("Rua das Motos, 100");
        patio.setTelefone(1199999999);

    }

    @Test
    void deveListarTodosOsPatios() {

        when(patioRepository.findAll()).thenReturn(Arrays.asList(patio));

        List<PatioEntity> lista = patioService.listarTodos();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals("Pátio Central", lista.get(0).getNome());
        verify(patioRepository, times(1)).findAll();

    }

    @Test
    void deveBuscarPatioPorId() {

        when(patioRepository.findById(1L)).thenReturn(Optional.of(patio));

        PatioEntity encontrado = patioService.buscarPorId(1L);

        assertNotNull(encontrado);
        assertEquals("Pátio Central", encontrado.getNome());
        assertEquals(1199999999, encontrado.getTelefone());
        verify(patioRepository, times(1)).findById(1L);

    }

    @Test
    void deveLancarExcecaoAoBuscarPatioInexistente() {

        when(patioRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> patioService.buscarPorId(2L));
        verify(patioRepository, times(1)).findById(2L);

    }

    @Test
    void deveCriarPatio() {

        when(patioRepository.save(any(PatioEntity.class))).thenReturn(patio);

        PatioEntity criado = patioService.criar(patio);

        assertNotNull(criado);
        assertEquals("Pátio Central", criado.getNome());
        assertEquals(1199999999, criado.getTelefone());
        verify(patioRepository, times(1)).save(patio);

    }

    @Test
    void deveAtualizarPatio() {

        when(patioRepository.findById(1L)).thenReturn(Optional.of(patio));
        when(patioRepository.save(any(PatioEntity.class))).thenReturn(patio);

        PatioEntity atualizado = new PatioEntity();
        atualizado.setNome("Pátio Norte");
        atualizado.setEndereco("Av. Nova, 200");
        atualizado.setTelefone(1188888888);

        PatioEntity resultado = patioService.atualizar(1L, atualizado);

        assertEquals("Pátio Norte", resultado.getNome());
        assertEquals("Av. Nova, 200", resultado.getEndereco());
        assertEquals(1188888888, resultado.getTelefone());
        verify(patioRepository, times(1)).save(patio);

    }

    @Test
    void deveDeletarPatio() {

        doNothing().when(patioRepository).deleteById(1L);

        patioService.deletar(1L);

        verify(patioRepository, times(1)).deleteById(1L);

    }

}
