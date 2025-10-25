package com.example.pricetracker.infra.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TrackerResponse(
    UUID id, 
    String url, 
    String store, 
    String selector,
    BigDecimal targetAmount, 
    String targetCurrency,
    String cron
) {}
