package com.example.pricetracker.domain.value;

import java.util.UUID;

public record TrackerId(UUID value) {
    public static TrackerId newId(){ 
        return new TrackerId(UUID.randomUUID());
    }
}