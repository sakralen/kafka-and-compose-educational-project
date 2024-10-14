package com.example.common.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostDto(

        UUID id,

        String content,

        LocalDateTime sentAt,

        LocalDateTime lastModifiedAt

) {
}
