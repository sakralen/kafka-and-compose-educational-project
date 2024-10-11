package com.example.postservice.service.impl;

import com.example.common.dto.AuditedPostDto;
import com.example.common.dto.PostDto;
import com.example.common.entity.Operation;
import com.example.postservice.service.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static com.example.common.util.TopicConstants.FROM_AUDIT_TOPIC;
import static com.example.common.util.TopicConstants.TO_AUDIT_TOPIC;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, AuditedPostDto> kafkaTemplate;

    @Override
    @KafkaListener(topics = FROM_AUDIT_TOPIC)
    public void listenForAuditedPost(AuditedPostDto auditedPostDto) {
        log.info("Audited post {} received back", auditedPostDto);
    }

    @Override
    public void sendForAudit(PostDto postDto, Operation operation) {
        AuditedPostDto postToAuditDto = new AuditedPostDto(null, postDto, operation);

        Message<AuditedPostDto> message = MessageBuilder
                .withPayload(postToAuditDto)
                .setHeader(TOPIC, TO_AUDIT_TOPIC)
                .build();

        kafkaTemplate.send(message);

        log.info("{} operation on {} sent for audit", operation, postDto);
    }

}
