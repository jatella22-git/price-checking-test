package com.example.pricetracker.infra.db;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="price_history")
@Getter
@Setter
public class PriceHistoryEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, columnDefinition="uuid")
    private UUID trackerId;

    @Column(nullable=false) private BigDecimal
    price;

    private String currency;

    private Boolean inStock;

    @Column(nullable=false)
    private Instant checkedAt;
}
