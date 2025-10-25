package com.example.pricetracker.domain.port;

import java.math.BigDecimal;

public interface PriceProviderPort {
    record Result(BigDecimal price, String currency, boolean inStock){}
    Result fetch(String url, String selectorOrNull);
}
