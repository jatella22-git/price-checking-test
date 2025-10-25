package com.example.pricetracker.infra.web;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pricetracker.app.CheckPriceUseCase;
import com.example.pricetracker.domain.port.TrackerRepoPort;
import com.example.pricetracker.domain.value.TrackerId;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceCheckController {
  private final TrackerRepoPort trackerRepo;
  private final CheckPriceUseCase checkUseCase;

  @PostMapping("/{id}/check")
  public Object check(@PathVariable UUID id) {
    var tracker = trackerRepo.findById(new TrackerId(id))
      .orElseThrow(() -> new IllegalArgumentException("Tracker not found"));
    var url = tracker.getUrl().value();
    var selector = tracker.selector().orElse(null);
    var currency = tracker.target().map(t -> t.currency()).orElse("EUR");
    return checkUseCase.handle(tracker.getId(), url, selector, currency);
  }
}
