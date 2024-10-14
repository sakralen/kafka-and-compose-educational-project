package com.example.auditservice.mapper;

import com.example.auditservice.entity.AuditedPost;
import com.example.common.dto.AuditedPostDto;
import com.example.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AuditedPostDtoMapper extends BaseMapper<AuditedPost, AuditedPostDto> {

    @Override
    @Mapping(source = "postId", target = "post.id")
    @Mapping(source = "content", target = "post.content")
    @Mapping(source = "sentAt", target = "post.sentAt")
    @Mapping(source = "lastModifiedAt", target = "post.lastModifiedAt")
    AuditedPostDto toDto(AuditedPost auditedPost);

    @Override
    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "post.content", target = "content")
    @Mapping(source = "post.sentAt", target = "sentAt")
    @Mapping(source = "post.lastModifiedAt", target = "lastModifiedAt")
    AuditedPost toEntity(AuditedPostDto auditedPostDto);

}
