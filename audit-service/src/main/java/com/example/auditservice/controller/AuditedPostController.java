package com.example.auditservice.controller;

import com.example.auditservice.service.AuditedPostCrudService;
import com.example.common.dto.AuditedPostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/audited")
public class AuditedPostController {

    private final AuditedPostCrudService auditedPostCrudService;

    @GetMapping("/{postId}")
    public List<AuditedPostDto> findPostHistoryById(@PathVariable UUID postId) {
        log.info("Received findPostHistoryById request");

        return auditedPostCrudService.findPostHistoryById(postId);
    }

    @GetMapping
    public List<AuditedPostDto> findAll() {
        log.info("Received findAll request");

        return auditedPostCrudService.findAll();
    }

}
