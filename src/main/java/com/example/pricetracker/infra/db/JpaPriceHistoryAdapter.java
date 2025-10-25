package com.example.pricetracker.infra.db;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.pricetracker.domain.PriceSnapshot;
import com.example.pricetracker.domain.port.PriceHistoryPort;
import com.example.pricetracker.domain.value.TrackerId;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaPriceHistoryAdapter implements PriceHistoryPort {
    private final PriceHistoryJpaRepository jpa;

    @Override
    public void save(PriceSnapshot s){
        var e = new PriceHistoryEntity();
        e.setTrackerId(s.trackerId().value());
        e.setPrice(s.price());
        e.setCurrency(s.currency());
        e.setInStock(s.inStock());
        e.setCheckedAt(s.checkedAt());
        jpa.save(e);
    }

    @Override
    public Optional<PriceSnapshot> findLast(TrackerId id){
        return jpa.findTop1ByTrackerIdOrderByCheckedAtDesc(id.value())
        .map(e -> new PriceSnapshot(new TrackerId(e.getTrackerId()), e.getPrice(), e.getCurrency(), Boolean.TRUE.equals(e.getInStock()), e.getCheckedAt()));
    }
}
