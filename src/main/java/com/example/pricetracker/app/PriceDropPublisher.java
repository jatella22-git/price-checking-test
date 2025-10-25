package com.example.pricetracker.app;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PriceDropPublisher {
  private final KafkaTemplate<String, Object> kafka;
  public void publish(UUID trackerId, BigDecimal price, String currency, Instant ts) {
    var event = new PriceDropEvent(trackerId, price, currency, ts);
    kafka.send("price.drop", trackerId.toString(), event);
  }
  public record PriceDropEvent(UUID trackerId, BigDecimal price, String currency, Instant at){}
}
