package com.example.common.dto;

import com.example.common.entity.Operation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

// TODO: ZonedDateTime

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AuditedPostDto {

    private UUID id;

    private Operation operation;

    private LocalDateTime auditedAt;

    private PostDto post;

}
