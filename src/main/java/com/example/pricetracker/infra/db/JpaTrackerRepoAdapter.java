package com.example.pricetracker.infra.db;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.pricetracker.domain.Tracker;
import com.example.pricetracker.domain.port.TrackerRepoPort;
import com.example.pricetracker.domain.value.Cron;
import com.example.pricetracker.domain.value.Price;
import com.example.pricetracker.domain.value.Store;
import com.example.pricetracker.domain.value.TrackerId;
import com.example.pricetracker.domain.value.TrackerUrl;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaTrackerRepoAdapter implements TrackerRepoPort {
    private final TrackerJpaRepository jpa;

    @Override
    public TrackerId save(Tracker t){
        var e = toEntity(t);
        jpa.save(e);
        return new TrackerId(e.getId());
    }

    @Override
    public Optional<Tracker> findById(TrackerId id){
        return jpa.findById(id.value()).map(this::toDomain);
    }

    @Override
    public List<Tracker> findAll(){
        return jpa.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void delete(TrackerId id){ jpa.deleteById(id.value()); }

    private TrackerEntity toEntity(Tracker t){
        var e = new TrackerEntity();
        e.setId(t.getId().value());
        e.setUrl(t.getUrl().value());
        e.setStore(t.getStore().name());
        e.setSelector(t.selector().orElse(null));
        e.setCron(t.getCron().value());
        e.setCreatedAt(t.getCreatedAt());
        t.target().ifPresent(m -> { e.setTargetAmount(m.amount());
        e.setTargetCurrency(m.currency()); });

        return e;
    }
    private Tracker toDomain(TrackerEntity e){
        Price price = (e.getTargetAmount()==null)? null : new Price(e.getTargetAmount(), e.getTargetCurrency());
        return new Tracker(
            new TrackerId(e.getId()),
            new TrackerUrl(e.getUrl()),
            Store.valueOf(e.getStore()),
            e.getSelector(),
            price,
            new Cron(e.getCron()),
            e.getCreatedAt()
        );
    }
}
