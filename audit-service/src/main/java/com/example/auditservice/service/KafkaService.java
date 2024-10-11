package com.example.auditservice.service;

import com.example.common.dto.AuditedPostDto;

public interface KafkaService {

    void listenForPost(AuditedPostDto auditedPostDto);

    void sendBackAudited(AuditedPostDto auditedPostDto);

}
