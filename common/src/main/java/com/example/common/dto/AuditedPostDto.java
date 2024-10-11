package com.example.common.dto;

import com.example.common.entity.Operation;

import java.util.UUID;

public record AuditedPostDto(

        UUID id,

        PostDto post,

        Operation operation

) {
}
