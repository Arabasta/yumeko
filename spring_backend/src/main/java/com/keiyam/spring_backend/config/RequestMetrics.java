package com.keiyam.spring_backend.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class RequestMetrics {
    private final Counter requestCounter;
    private final Timer requestTimer;

    public RequestMetrics(MeterRegistry registry) {
        this.requestCounter = Counter.builder("coin.change.requests")
                .description("Total number of coin change requests")
                .register(registry);
        this.requestTimer = Timer.builder("coin.change.request.duration")
                .description("Time taken to process coin change requests")
                .register(registry);
    }

    public void incrementRequestCount() {
        requestCounter.increment();
    }

    public void recordRequestDuration(long duration, TimeUnit unit) {
        requestTimer.record(duration, unit);
    }
}