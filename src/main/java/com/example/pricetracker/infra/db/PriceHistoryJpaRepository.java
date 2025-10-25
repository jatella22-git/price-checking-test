package com.example.pricetracker.infra.db;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceHistoryJpaRepository extends JpaRepository<PriceHistoryEntity, Long> {
  Optional<PriceHistoryEntity> findTop1ByTrackerIdOrderByCheckedAtDesc(UUID trackerId);
}
