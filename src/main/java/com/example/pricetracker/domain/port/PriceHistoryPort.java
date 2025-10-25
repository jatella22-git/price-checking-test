package com.example.pricetracker.domain.port;

import java.util.Optional;
import com.example.pricetracker.domain.PriceSnapshot;
import com.example.pricetracker.domain.value.TrackerId;

public interface PriceHistoryPort {
    void save(PriceSnapshot snapshot);
    Optional<PriceSnapshot> findLast(TrackerId id);
}
