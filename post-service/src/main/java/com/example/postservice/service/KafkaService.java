package com.example.postservice.service;

import com.example.common.dto.PostDto;
import com.example.common.entity.Operation;

public interface KafkaService {

    void sendForAudit(PostDto dto, Operation operation);

}
