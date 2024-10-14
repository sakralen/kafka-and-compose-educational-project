package com.example.auditservice.repository;

import com.example.auditservice.entity.AuditedPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuditedPostRepository extends JpaRepository<AuditedPost, UUID> {

    List<AuditedPost> findByPostId(UUID postId);

}
