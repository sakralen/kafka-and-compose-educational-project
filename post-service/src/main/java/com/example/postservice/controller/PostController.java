package com.example.postservice.controller;

import com.example.common.dto.PostDto;
import com.example.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(CREATED)
    public PostDto create(@RequestBody @Validated PostDto createDto) {
        log.info("Received create request for {}", createDto);

        return postService.create(createDto)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST));
    }

    @GetMapping("/{id}")
    public PostDto findById(@PathVariable UUID id) {
        log.info("Received findById request for {}", id);

        return postService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @GetMapping
    public List<PostDto> findAll() {
        log.info("Received findAll request");

        return postService.findAll();
    }

    @PutMapping("/{id}")
    public PostDto update(@PathVariable UUID id, @RequestBody @Validated PostDto updateDto) {
        log.info("Received update request");

        return postService.update(id, updateDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        if (postService.delete(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

}
