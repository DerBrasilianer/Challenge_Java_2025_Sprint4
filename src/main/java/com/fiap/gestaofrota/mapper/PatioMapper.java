package com.fiap.gestaofrota.mapper;

import com.fiap.gestaofrota.dto.PatioDTO;
import com.fiap.gestaofrota.entity.PatioEntity;

public class PatioMapper {

    public static PatioDTO toPatioDTO(PatioEntity entity) {

        if (entity == null) return null;

        PatioDTO dto = new PatioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEndereco(entity.getEndereco());
        dto.setTelefone(entity.getTelefone());

        return dto;

    }

    public static PatioEntity toPatioEntity(PatioDTO dto) {

        if (dto == null) return null;

        PatioEntity entity = new PatioEntity();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEndereco(dto.getEndereco());
        entity.setTelefone(dto.getTelefone());

        return entity;

    }

}
