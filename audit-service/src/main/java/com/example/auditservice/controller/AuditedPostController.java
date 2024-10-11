package com.example.auditservice.controller;

import com.example.auditservice.service.AuditedPostCrudService;
import com.example.common.dto.AuditedPostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/audited")
public class AuditedPostController {

    private final AuditedPostCrudService auditedPostCrudService;

    @GetMapping
    public List<AuditedPostDto> findAll() {
        log.info("Received findAll request");

        return auditedPostCrudService.findAll();
    }

}
