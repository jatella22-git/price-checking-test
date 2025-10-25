package com.example.pricetracker.domain;

import com.example.pricetracker.domain.value.TrackerId;
import java.math.BigDecimal;
import java.time.Instant;

public record PriceSnapshot(TrackerId trackerId, BigDecimal price, String currency, boolean inStock, Instant checkedAt) {}
