package com.example.pricetracker.app;

import com.example.pricetracker.domain.port.TrackerRepoPort;
import com.example.pricetracker.domain.value.TrackerId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckScheduler {

  private final TrackerRepoPort trackers;
  private final CheckPriceUseCase checkPrice;

  // Cada 30 minutos (segundo 0)
  @Scheduled(cron = "0 */30 * * * *")
  public void run() {
    var list = trackers.findAll();
    log.info("Scheduled check: {} trackers", list.size());
    list.forEach(t -> {
        try {
            var url = t.getUrl().value();
            var selector = t.selector().orElse(null);
            var currency = t.target().map(x -> x.currency()).orElse("EUR");
            checkPrice.handle(new TrackerId(t.getId().value()), url, selector, currency);
        } catch (Exception e) {
            log.warn("Check failed for {}: {}", t.getId().value(), e.getMessage());
        }
    });
  }
}
