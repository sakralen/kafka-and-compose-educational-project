package com.example.postservice.entity;

import com.example.common.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Post extends AbstractEntity {

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime sentAt;

    @Column(nullable = false)
    private LocalDateTime lastModifiedAt;

    @PrePersist
    protected void prePersist() {
        sentAt = LocalDateTime.now();
        lastModifiedAt = sentAt;
    }

    @PreUpdate
    protected void preUpdate() {
        lastModifiedAt = LocalDateTime.now();
    }

}
