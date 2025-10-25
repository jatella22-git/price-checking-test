package com.example.pricetracker.infra.db;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tracker")
@Getter
@Setter
public class TrackerEntity {
    @Id
    @Column(columnDefinition="uuid")
    private UUID id;

    @Column(nullable=false, length=2048)
    private String url;

    @Column(nullable=false, length=32)
    private String store;

    private String selector;

    private BigDecimal targetAmount;

    private String targetCurrency;

    @Column(nullable=false)
    private String cron;

    @Column(nullable=false)
    private Instant createdAt;
}
