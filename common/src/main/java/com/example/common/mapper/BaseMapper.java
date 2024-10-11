package com.example.common.mapper;

import com.example.common.entity.AbstractEntity;

public interface BaseMapper<E extends AbstractEntity, DTO> {

    E toEntity(DTO dto);

    DTO toDto(E entity);

}
