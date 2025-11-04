package com.fiap.gestaofrota.mapper;

import com.fiap.gestaofrota.dto.MotoDTO;
import com.fiap.gestaofrota.entity.MotoEntity;
import com.fiap.gestaofrota.entity.PatioEntity;

public class MotoMapper {

    public static MotoDTO toMotoDTO(MotoEntity entity) {

        if (entity == null) return null;

        MotoDTO dto = new MotoDTO();
        dto.setId(entity.getId());
        dto.setMarca(entity.getMarca());
        dto.setModelo(entity.getModelo());
        dto.setPlaca(entity.getPlaca());
        dto.setAno(entity.getAno());
        dto.setNumeroIot(entity.getNumeroIot());

        if (entity.getPatio() != null) {

            dto.setPatioId(entity.getPatio().getId());
            dto.setPatioNome(entity.getPatio().getNome());

        }

        return dto;

    }

    public static MotoEntity toMotoEntity(MotoDTO dto, PatioEntity patio) {

        if (dto == null) return null;

        MotoEntity entity = new MotoEntity();
        entity.setId(dto.getId());
        entity.setMarca(dto.getMarca());
        entity.setModelo(dto.getModelo());
        entity.setPlaca(dto.getPlaca());
        entity.setAno(dto.getAno());
        entity.setNumeroIot(dto.getNumeroIot());
        entity.setPatio(patio);

        return entity;

    }
}
