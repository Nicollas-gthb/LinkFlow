package com.linkflow.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "delivery_attempts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private WebhookEvent event;

    @Column(nullable = false, length = 20)
    private AttemptedStatus status;

    @Column(name = "response_code")
    private Integer responseCode;

    @Column(name = "response_body", columnDefinition = "TEXT")
    private String responseBody;

    @Column(name = "error_message", length = 500)
    private String errorMessage;

    @Column(name = "latency_ms", columnDefinition = "BIGINT")
    private Long latencyMs;

    @Column(name = "attempted_at", nullable = false)
    private OffsetDateTime attemptedAt;

    public enum AttemptedStatus {
        SUCCESS, FAILED
    }
}
