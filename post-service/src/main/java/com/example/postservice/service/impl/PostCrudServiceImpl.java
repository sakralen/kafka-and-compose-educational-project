package com.example.postservice.service.impl;

import com.example.common.dto.PostDto;
import com.example.postservice.entity.Post;
import com.example.postservice.mapper.PostDtoMapper;
import com.example.postservice.repository.PostRepository;
import com.example.postservice.service.PostCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostCrudServiceImpl implements PostCrudService {

    private final PostRepository postRepository;

    private final PostDtoMapper postDtoMapper;

    @Override
    @Transactional
    public Optional<PostDto> create(PostDto createDto) {
        Post postToSave = postDtoMapper.toEntity(createDto);

        Post savedPost = postRepository.save(postToSave);

        log.info("Saved post {}", savedPost);

        return Optional.of(postDtoMapper.toDto(savedPost));
    }

    @Override
    public Optional<PostDto> findById(UUID id) {
        Optional<Post> foundPost = postRepository.findById(id);

        if (foundPost.isPresent()) {
            log.info("Found post {}", foundPost.get());
        } else {
            log.info("post with id {} not found", id);
        }

        return foundPost.map(postDtoMapper::toDto);
    }

    @Override
    public List<PostDto> findAll() {
        List<Post> foundPosts = postRepository.findAll();

        log.info("Found posts {}", foundPosts);

        return foundPosts.stream()
                .map(postDtoMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public Optional<PostDto> update(UUID id, PostDto updateDto) {
        Optional<Post> foundPost = postRepository.findById(id);

        if (foundPost.isPresent()) {
            Post post = foundPost.get();

            post.setContent(updateDto.content());

            postRepository.saveAndFlush(post);

            log.info("Updated post with id {} with {}", id, updateDto);
        }

        return foundPost.map(postDtoMapper::toDto);
    }

    @Override
    @Transactional
    public Optional<PostDto> delete(UUID id) {
        Optional<Post> foundPost = postRepository.findById(id);

        if (foundPost.isPresent()) {
            Post post = foundPost.get();

            postRepository.delete(post);
            postRepository.flush();

            log.info("Deleted post {}", post);
        }

        return foundPost.map(postDtoMapper::toDto);
    }

}
