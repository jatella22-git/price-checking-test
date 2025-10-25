package com.example.pricetracker.domain.port;

import java.util.List;
import java.util.Optional;
import com.example.pricetracker.domain.Tracker;
import com.example.pricetracker.domain.value.TrackerId;

public interface TrackerRepoPort {
    TrackerId save(Tracker tracker);
    Optional<Tracker> findById(TrackerId id);
    List<Tracker> findAll();
    void delete(TrackerId id);
}
