package com.example.pricetracker.infra.web.dto;

import java.math.BigDecimal;

public record CreateTrackerRequest(
    String url, 
    String store, 
    String selector,
    BigDecimal targetAmount, 
    String targetCurrency,
    String cron
) {}
