package com.example.auditservice.service.impl;

import com.example.auditservice.service.AuditedPostCrudService;
import com.example.auditservice.service.KafkaService;
import com.example.common.dto.AuditedPostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.common.util.TopicConstants.TO_AUDIT_TOPIC;
import static org.springframework.kafka.support.KafkaHeaders.CORRELATION_ID;
import static org.springframework.kafka.support.KafkaHeaders.REPLY_TOPIC;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaServiceImpl implements KafkaService {

    private final AuditedPostCrudService auditedPostCrudService;

    private final KafkaTemplate<String, AuditedPostDto> kafkaTemplate;

    @Override
    @KafkaListener(topics = TO_AUDIT_TOPIC)
    public void listenForPost(ConsumerRecord<String, AuditedPostDto> record) {
        AuditedPostDto auditedPostDto = record.value();
        byte[] replyTopic = record.headers().lastHeader(REPLY_TOPIC).value();
        byte[] correlationId = record.headers().lastHeader(CORRELATION_ID).value();

        log.info("Received post {} for audit", auditedPostDto);

        Optional<AuditedPostDto> savedAuditedPostDto = auditedPostCrudService.audit(auditedPostDto);

        savedAuditedPostDto.ifPresent(postDto -> sendBackAudited(postDto, replyTopic, correlationId));
    }

    @Override
    public void sendBackAudited(AuditedPostDto savedAuditedPostDto, byte[] replyTopic, byte[] correlationId) {
        Message<AuditedPostDto> message = MessageBuilder
                .withPayload(savedAuditedPostDto)
                .setHeader(TOPIC, replyTopic)
                .setHeader(CORRELATION_ID, correlationId)
                .build();

        kafkaTemplate.send(message);

        log.info("Audited post {} sent back", savedAuditedPostDto);
    }

}
