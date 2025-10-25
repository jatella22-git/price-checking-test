package com.example.pricetracker.domain.value;

import java.math.BigDecimal;

public record Price(BigDecimal amount, String currency) {
    public Price {
        if (amount == null || amount.signum() < 0) throw new IllegalArgumentException("amount>=0");
        if (currency == null || currency.isBlank()) throw new IllegalArgumentException("currency required");
    }
}