package com.example.pricetracker.infra.html;

import com.example.pricetracker.domain.port.PriceProviderPort;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Slf4j
@Component
public class HtmlPriceProvider implements PriceProviderPort {
  @Override
  public Result fetch(String url, String selectorOrNull) {
    try {
        if (selectorOrNull==null || selectorOrNull.isBlank())
            throw new IllegalArgumentException("CSS selector is required for GENERIC");
        Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(8000).get();
        Element el = doc.selectFirst(selectorOrNull);
            if (el==null) throw new RuntimeException("Element not found with selector: " + selectorOrNull);
        String raw = el.text().replaceAll("[^0-9,\\.]", "").replace(",", ".");
        return new Result(new BigDecimal(raw), "EUR", true);
    } catch (Exception e) {
        log.error("Error fetching price from {}: {}", url, e.getMessage());
        throw new RuntimeException(e);
    }
  }
}