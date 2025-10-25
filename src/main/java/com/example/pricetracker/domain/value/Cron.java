package com.example.pricetracker.domain.value;

public record Cron(String value) {
    public Cron {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("cron required");
    }

    public static Cron every30m(){ 
        return new Cron("0 */30 * * * *"); 
    }
}
