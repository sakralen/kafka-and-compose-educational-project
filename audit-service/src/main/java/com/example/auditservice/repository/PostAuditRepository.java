package com.example.auditservice.repository;

import com.example.auditservice.entity.AuditedPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostAuditRepository extends JpaRepository<AuditedPost, UUID> {
}
