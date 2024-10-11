package com.example.auditservice.service;

import com.example.common.dto.AuditedPostDto;

import java.util.List;
import java.util.Optional;

public interface AuditedPostCrudService {

    Optional<AuditedPostDto> audit(AuditedPostDto auditedPostDto);

    List<AuditedPostDto> findAll();

}
