package com.example.postservice.service.impl;

import com.example.common.dto.PostDto;
import com.example.postservice.service.KafkaService;
import com.example.postservice.service.PostCrudService;
import com.example.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.common.entity.Operation.CREATE;
import static com.example.common.entity.Operation.DELETE;
import static com.example.common.entity.Operation.UPDATE;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostCrudService postCrudService;

    private final KafkaService kafkaService;

    @Override
    public Optional<PostDto> create(PostDto createDto) {
        Optional<PostDto> postDto = postCrudService.create(createDto);

        postDto.ifPresent(dto -> kafkaService.sendForAudit(dto, CREATE));

        return postDto;
    }

    @Override
    public Optional<PostDto> findById(UUID id) {
        return postCrudService.findById(id);
    }

    @Override
    public List<PostDto> findAll() {
        return postCrudService.findAll();
    }

    @Override
    public Optional<PostDto> update(UUID id, PostDto updateDto) {
        Optional<PostDto> postDto = postCrudService.update(id, updateDto);

        postDto.ifPresent(dto -> kafkaService.sendForAudit(dto, UPDATE));

        return postDto;
    }

    @Override
    public Optional<PostDto> delete(UUID id) {
        Optional<PostDto> postDto = postCrudService.delete(id);

        postDto.ifPresent(dto -> kafkaService.sendForAudit(dto, DELETE));

        return postDto;
    }

}
