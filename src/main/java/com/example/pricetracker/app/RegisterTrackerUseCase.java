package com.example.pricetracker.app;

import org.springframework.stereotype.Service;
import com.example.pricetracker.domain.Tracker;
import com.example.pricetracker.domain.port.TrackerRepoPort;
import com.example.pricetracker.domain.value.Cron;
import com.example.pricetracker.domain.value.Price;
import com.example.pricetracker.domain.value.Store;
import com.example.pricetracker.domain.value.TrackerId;
import com.example.pricetracker.domain.value.TrackerUrl;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterTrackerUseCase {
    private final TrackerRepoPort repo;

    public TrackerId handle(TrackerUrl url, Store store, String selector, Price price, Cron cron){
        Tracker t = Tracker.create(url, store, selector, price, cron);
        return repo.save(t);
    }
}
