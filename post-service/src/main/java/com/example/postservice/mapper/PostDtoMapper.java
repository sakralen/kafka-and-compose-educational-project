package com.example.postservice.mapper;

import com.example.common.dto.PostDto;
import com.example.common.mapper.BaseMapper;
import com.example.postservice.entity.Post;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PostDtoMapper extends BaseMapper<Post, PostDto> {
}
