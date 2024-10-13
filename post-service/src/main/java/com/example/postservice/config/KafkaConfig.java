package com.example.postservice.config;

import com.example.common.dto.AuditedPostDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import static com.example.common.util.TopicConstants.FROM_AUDIT_TOPIC;

@Configuration
public class KafkaConfig {

    @Bean
    public ReplyingKafkaTemplate<String, AuditedPostDto, AuditedPostDto> replyingKafkaTemplate(
            ProducerFactory<String, AuditedPostDto> producerFactory,
            ConcurrentMessageListenerContainer<String, AuditedPostDto> repliesContainer) {
        return new ReplyingKafkaTemplate<>(producerFactory, repliesContainer);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, AuditedPostDto> repliesContainer(
            ConsumerFactory<String, AuditedPostDto> consumerFactory) {
        ContainerProperties containerProperties = new ContainerProperties(FROM_AUDIT_TOPIC);

        return new ConcurrentMessageListenerContainer<>(consumerFactory, containerProperties);
    }

}
