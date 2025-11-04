package com.fiap.gestaofrota.dto;

import com.fiap.gestaofrota.enums.Marcas;
import com.fiap.gestaofrota.enums.Modelos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MotoDTO {

    private Long id;

    @NotNull
    private Marcas marca;

    @NotNull
    private Modelos modelo;

    @NotBlank
    private String placa;

    @NotNull
    private Integer ano;

    @NotNull
    private Long numeroIot;

    @NotNull
    private Long patioId;

    private String patioNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public Modelos getModelo() {
        return modelo;
    }

    public void setModelo(Modelos modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Long getNumeroIot() {
        return numeroIot;
    }

    public void setNumeroIot(Long numeroIot) {
        this.numeroIot = numeroIot;
    }

    public Long getPatioId() {
        return patioId;
    }

    public void setPatioId(Long patioId) {
        this.patioId = patioId;
    }

    public String getPatioNome() {
        return patioNome;
    }

    public void setPatioNome(String patioNome) {
        this.patioNome = patioNome;
    }

}
