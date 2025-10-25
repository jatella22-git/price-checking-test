package com.example.pricetracker.infra.db;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackerJpaRepository extends JpaRepository<TrackerEntity, UUID> {}
