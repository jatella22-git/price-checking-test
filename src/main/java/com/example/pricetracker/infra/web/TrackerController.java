package com.example.pricetracker.infra.web;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pricetracker.app.RegisterTrackerUseCase;
import com.example.pricetracker.domain.Tracker;
import com.example.pricetracker.domain.port.TrackerRepoPort;
import com.example.pricetracker.domain.value.Cron;
import com.example.pricetracker.domain.value.Store;
import com.example.pricetracker.domain.value.TrackerId;
import com.example.pricetracker.domain.value.TrackerUrl;
import com.example.pricetracker.domain.value.Price;
import com.example.pricetracker.infra.web.dto.CreateTrackerRequest;
import com.example.pricetracker.infra.web.dto.TrackerResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trackers")
@RequiredArgsConstructor
public class TrackerController {
    private final RegisterTrackerUseCase register;
    private final TrackerRepoPort repo;

    @PostMapping
    public TrackerResponse create(@RequestBody CreateTrackerRequest r){
        TrackerId id = register.handle(
            new TrackerUrl(r.url()),
            Store.valueOf(r.store()),
            r.selector(),
            (r.targetAmount()==null? null : new Price(r.targetAmount(), Optional.ofNullable(r.targetCurrency()).orElse("EUR"))),
            (r.cron()==null? null : new Cron(r.cron()))
        );
        
        var t = repo.findById(id).orElseThrow();
        return toDto(t);
    }

    @GetMapping
    public List<TrackerResponse> list(){
        return repo.findAll().stream().map(this::toDto).toList();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){ 
        repo.delete(new TrackerId(id));
    }

    private TrackerResponse toDto(Tracker t){
        var target = t.target().orElse(null);
        return new TrackerResponse(
            t.getId().value(),
            t.getUrl().value(),
            t.getStore().name(),
            t.selector().orElse(null),
            target==null? null : target.amount(),
            target==null? null : target.currency(),
            t.getCron().value()
        );
    }
}
