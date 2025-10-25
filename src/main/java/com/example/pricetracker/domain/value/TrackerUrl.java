package com.example.pricetracker.domain.value;

public record TrackerUrl(String value) {
    public TrackerUrl {
        if (value == null || !value.startsWith("http")) throw new IllegalArgumentException("Invalid URL");
    }
}
