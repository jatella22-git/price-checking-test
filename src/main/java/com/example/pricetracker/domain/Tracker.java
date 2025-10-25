package com.example.pricetracker.domain;

import java.time.Instant;
import java.util.Optional;
import com.example.pricetracker.domain.value.Cron;
import com.example.pricetracker.domain.value.Price;
import com.example.pricetracker.domain.value.Store;
import com.example.pricetracker.domain.value.TrackerId;
import com.example.pricetracker.domain.value.TrackerUrl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tracker {
    private final TrackerId id;
    private final TrackerUrl url;
    private final Store store;
    private final String selector;
    private final Price target;
    private final Cron cron;
    private final Instant createdAt;

    public static Tracker create(TrackerUrl url, Store store, String selector, Price target, Cron cron){
        return new Tracker(TrackerId.newId(), url, store, selector, target, cron==null? Cron.every30m(): cron, Instant.now());
    }
    
    public Optional<String> selector(){ return Optional.ofNullable(selector); }
    public Optional<Price> target(){ return Optional.ofNullable(target); }

}
