package com.linkflow.repository;

import com.linkflow.domain.WebhookEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WebhookEventRepository extends JpaRepository<WebhookEvent, UUID> {

    Page<WebhookEvent> findByIntegrationId(UUID integrationId, Pageable pageable);

    Page<WebhookEvent> findByIntegrationIdAndStatus(UUID integrationId, WebhookEvent.EventStatus status, Pageable pageable);
}
