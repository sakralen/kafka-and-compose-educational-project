package com.example.auditservice.service.impl;

import com.example.auditservice.entity.AuditedPost;
import com.example.auditservice.mapper.AuditedPostDtoMapper;
import com.example.auditservice.repository.PostAuditRepository;
import com.example.auditservice.service.AuditedPostCrudService;
import com.example.common.dto.AuditedPostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuditedPostCrudServiceImpl implements AuditedPostCrudService {

    private final PostAuditRepository postAuditRepository;

    private final AuditedPostDtoMapper auditedPostDtoMapper;

    @Override
    @Transactional
    public Optional<AuditedPostDto> audit(AuditedPostDto auditedPostDto) {
        AuditedPost auditedPostToSave = auditedPostDtoMapper.toEntity(auditedPostDto);

        AuditedPost savedAuditedPost = postAuditRepository.save(auditedPostToSave);

        log.info("Audited post {}", savedAuditedPost);

        return Optional.of(auditedPostDtoMapper.toDto(savedAuditedPost));
    }

    @Override
    public List<AuditedPostDto> findAll() {
        List<AuditedPost> auditedPosts = postAuditRepository.findAll();

        log.info("Found audited posts {}", auditedPosts);

        return auditedPosts.stream()
                .map(auditedPostDtoMapper::toDto)
                .toList();
    }

}
