package com.example.auditservice.service;

import com.example.common.dto.AuditedPostDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaService {

    void listenForPost(ConsumerRecord<String, AuditedPostDto> record);

    void sendBackAudited(AuditedPostDto savedAuditedPostDto, byte[] replyTopic, byte[] correlationId);

}
