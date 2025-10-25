package com.example.pricetracker.infra.messaging;

import com.example.pricetracker.app.PriceDropPublisher.PriceDropEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DropConsumer {

  @KafkaListener(topics = "price.drop", groupId = "price-tracker-log")
  public void receive(PriceDropEvent ev) {
    log.info("📉 PRICE DROP: {} → {} {}", ev.trackerId(), ev.price(), ev.currency());
  }
}
