package com.example.auditservice.service.impl;

import com.example.auditservice.entity.AuditedPost;
import com.example.auditservice.mapper.AuditedPostDtoMapper;
import com.example.auditservice.repository.AuditedPostRepository;
import com.example.auditservice.service.AuditedPostCrudService;
import com.example.common.dto.AuditedPostDto;
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
public class AuditedPostCrudServiceImpl implements AuditedPostCrudService {

    private final AuditedPostRepository auditedPostRepository;

    private final AuditedPostDtoMapper auditedPostDtoMapper;

    @Override
    @Transactional
    public Optional<AuditedPostDto> audit(AuditedPostDto auditedPostDto) {
        AuditedPost auditedPostToSave = auditedPostDtoMapper.toEntity(auditedPostDto);

        AuditedPost savedAuditedPost = auditedPostRepository.save(auditedPostToSave);

        log.info("Audited post {}", savedAuditedPost);

        return Optional.of(auditedPostDtoMapper.toDto(savedAuditedPost));
    }

    @Override
    public List<AuditedPostDto> findAll() {
        List<AuditedPost> auditedPosts = auditedPostRepository.findAll();

        log.info("Found audited posts {}", auditedPosts);

        return auditedPosts.stream()
                .map(auditedPostDtoMapper::toDto)
                .toList();
    }

    @Override
    public List<AuditedPostDto> findPostHistoryById(UUID postId) {
        List<AuditedPost> postHistory = auditedPostRepository.findByPostId(postId);

        log.info("Found post history: {}", postHistory);

        return postHistory.stream()
                .map(auditedPostDtoMapper::toDto)
                .toList();
    }

}
