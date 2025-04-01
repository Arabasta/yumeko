package com.keiyam.spring_backend.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


@Component
public class RequestMetrics {
    private final MeterRegistry registry;

    private final Counter requestCounter;
    private final Timer requestTimer;
    private final DistributionSummary responseSizeSummary;
    private final Counter successCounter;
    private final Counter failureCounter;

    public RequestMetrics(MeterRegistry registry) {
        this.registry = registry;

        // main request counter with common tags
        this.requestCounter = Counter.builder("api.requests.total")
                .description("Total number of API requests")
                .tag("application", "spring-backend")
                .register(registry);

        // request timer with histogram buckets
        this.requestTimer = Timer.builder("api.request.duration")
                .description("Time taken to process API requests")
                .publishPercentiles(0.5, 0.95, 0.99)
                .publishPercentileHistogram()
                .serviceLevelObjectives(
                        Duration.ofMillis(100),
                        Duration.ofMillis(500),
                        Duration.ofMillis(1000)
                )
                .tag("application", "coin-change-service")
                .register(registry);

        this.responseSizeSummary = DistributionSummary.builder("api.response.size")
                .description("Size of API responses")
                .baseUnit("bytes")
                .publishPercentiles(0.5, 0.95)
                .register(registry);

        this.successCounter = Counter.builder("api.requests.success")
                .description("Number of successful API requests")
                .tag("application", "coin-change-service")
                .register(registry);

        this.failureCounter = Counter.builder("api.requests.failure")
                .description("Number of failed API requests")
                .tag("application", "coin-change-service")
                .register(registry);
    }

    public void incrementRequestCount(String controllerName, String methodName) {
        // increment general counter
        requestCounter.increment();
        // increment method counter
        registry.counter("api.requests.method.total",
                        "controller", controllerName,
                        "method", methodName)
                .increment();
    }

    public void recordRequestDuration(long duration, TimeUnit unit,
                                      String controllerName, String methodName) {
        // record general timer
        requestTimer.record(duration, unit);

        // record with method tag
        Timer.builder("api.request.duration.method")
                .tags("controller", controllerName, "method", methodName)
                .register(registry)
                .record(duration, unit);
    }

    public void recordResponseSize(String controllerName, String methodName, long size) {
        responseSizeSummary.record(size);
        DistributionSummary.builder("api.response.size.method")
                .tags("controller", controllerName, "method", methodName)
                .register(registry)
                .record(size);
    }

    public void recordSuccess(String controllerName, String methodName) {
        successCounter.increment();
        registry.counter("api.requests.method.success",
                        "controller", controllerName,
                        "method", methodName)
                .increment();
    }

    public void recordFailure(String controllerName, String methodName, String errorType) {
        failureCounter.increment();
        registry.counter("api.requests.method.failure",
                        "controller", controllerName,
                        "method", methodName,
                        "error", errorType)
                .increment();
    }
}