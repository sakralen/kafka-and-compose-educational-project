package com.example.postservice.mapper;

import com.example.common.dto.AuditedPostDto;
import com.example.common.mapper.BaseMapper;
import com.example.postservice.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface AuditedPostDtoMapper extends BaseMapper<Post, AuditedPostDto> {

    @Override
    @Mapping(source = "id", target = "post.id")
    @Mapping(source = "content", target = "post.content")
    @Mapping(source = "sentAt", target = "post.sentAt")
    @Mapping(source = "lastModifiedAt", target = "post.lastModifiedAt")
    AuditedPostDto toDto(Post post);

    @Override
    @Mapping(source = "post.id", target = "id")
    @Mapping(source = "post.content", target = "content")
    @Mapping(source = "post.sentAt", target = "sentAt")
    @Mapping(source = "post.lastModifiedAt", target = "lastModifiedAt")
    Post toEntity(AuditedPostDto auditedPostDto);

}
