package com.example.postservice.service;

import com.example.common.dto.PostDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostCrudService {

    Optional<PostDto> create(PostDto createDto);

    Optional<PostDto> findById(UUID id);

    List<PostDto> findAll();

    Optional<PostDto> update(UUID id, PostDto updateDto);

    Optional<PostDto> delete(UUID id);

}
