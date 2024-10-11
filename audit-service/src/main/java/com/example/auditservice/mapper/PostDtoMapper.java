package com.example.auditservice.mapper;

import com.example.auditservice.entity.AuditedPost;
import com.example.common.dto.PostDto;
import com.example.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE)
public interface PostDtoMapper extends BaseMapper<AuditedPost, PostDto> {

    @Override
    @Mapping(source = "refId", target = "id")
    PostDto toDto(AuditedPost auditedPost);

    @Override
    @Mapping(source = "id", target = "refId")
    AuditedPost toEntity(PostDto postDto);

}
