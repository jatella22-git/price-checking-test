package com.example.pricetracker.app;

import java.time.Instant;
import org.springframework.stereotype.Service;
import com.example.pricetracker.domain.PriceSnapshot;
import com.example.pricetracker.domain.port.PriceHistoryPort;
import com.example.pricetracker.domain.port.PriceProviderPort;
import com.example.pricetracker.domain.value.TrackerId;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckPriceUseCase {
    private final PriceProviderPort provider;
    private final PriceHistoryPort history;

    public PriceSnapshot handle(TrackerId trackerId, String url, String selector, String currency) {
        var res = provider.fetch(url, selector);
        var snap = new PriceSnapshot(trackerId, res.price(), currencyOr(currency, res), res.inStock(), Instant.now());
        history.save(snap);
        return snap;
    }

    private String currencyOr(String preferred, PriceProviderPort.Result res){
        return (preferred!=null && !preferred.isBlank()) ? preferred : (res.currency()==null? "EUR" : res.currency());
    }
}
