package com.example.postservice.service.impl;

import com.example.common.dto.AuditedPostDto;
import com.example.common.dto.PostDto;
import com.example.common.entity.Operation;
import com.example.postservice.entity.Post;
import com.example.postservice.mapper.AuditedPostDtoMapper;
import com.example.postservice.mapper.PostDtoMapper;
import com.example.postservice.service.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.TimeoutException;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyMessageFuture;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static com.example.common.util.TopicConstants.FROM_AUDIT_TOPIC;
import static com.example.common.util.TopicConstants.TO_AUDIT_TOPIC;
import static org.springframework.kafka.support.KafkaHeaders.REPLY_TOPIC;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaServiceImpl implements KafkaService {

    private final ReplyingKafkaTemplate<String, AuditedPostDto, AuditedPostDto> replyingKafkaTemplate;

    private final PostDtoMapper postDtoMapper;

    private final AuditedPostDtoMapper auditedPostDtoMapper;

    @Override
    public void sendForAudit(PostDto postDto, Operation operation) {
        Post post = postDtoMapper.toEntity(postDto);
        AuditedPostDto postToAuditDto = auditedPostDtoMapper.toDto(post);
        postToAuditDto.setOperation(operation);

        Message<AuditedPostDto> message = MessageBuilder
                .withPayload(postToAuditDto)
                .setHeader(TOPIC, TO_AUDIT_TOPIC)
                .setHeader(REPLY_TOPIC, FROM_AUDIT_TOPIC)
                .build();

        RequestReplyMessageFuture<String, AuditedPostDto> reply = replyingKafkaTemplate.sendAndReceive(message);

        log.info("{} operation on {} sent for audit", operation, postDto);

        try {
            Message<?> repliedMessage = reply.get();
            log.info("Audit service replied with: {}", repliedMessage.getPayload());
        } catch (InterruptedException | ExecutionException | TimeoutException exception) {
            log.info("Audit reply exception: {}", exception.getMessage());
        }
    }

}
