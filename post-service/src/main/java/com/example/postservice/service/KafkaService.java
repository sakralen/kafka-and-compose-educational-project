package com.example.postservice.service;

import com.example.common.dto.AuditedPostDto;
import com.example.common.dto.PostDto;
import com.example.common.entity.Operation;

public interface KafkaService {

    void listenForAuditedPost(AuditedPostDto auditedPostDto);

    void sendForAudit(PostDto dto, Operation operation);

}
