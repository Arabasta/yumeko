package com.keiyam.spring_backend.controller;

import com.keiyam.spring_backend.dto.CoinChangeRequest;
import com.keiyam.spring_backend.dto.CoinChangeResponse;
import com.keiyam.spring_backend.metrics.RequestMetrics;
import com.keiyam.spring_backend.service.CoinChangeServiceFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * REST controller for handling coin change requests.
 */
@RestController
@RequestMapping("/api/v1/coin-change")
@RequiredArgsConstructor
@Tag(name = "Coin Change API", description = "API for calculating minimum coins for a given amount")
public class CoinChangeControllerV1 {
    private static final Logger logger = LoggerFactory.getLogger(CoinChangeControllerV1.class);
    private final CoinChangeServiceFactory coinChangeServiceFactory;
    private final RequestMetrics requestMetrics;

    /**
     * Endpoint to get the minimum number of coins needed to make up the given amount.
     *
     * @param request the request containing the amount and denominations
     * @return a ResponseEntity containing the list of coins in ascending order
     */
    @Operation(summary = "Calculate minimum coins", description = "Calculate the minimum number of coins needed to make up the given amount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully calculated coins",
                    content = @Content(schema = @Schema(implementation = CoinChangeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("minimum-coins")
    public ResponseEntity<CoinChangeResponse> getMinimumCoins(@Valid @RequestBody CoinChangeRequest request) {
        logger.info("Received request to calculate minimum coins for amount: {}", request.getAmount());
        long startTime = System.nanoTime();
        requestMetrics.incrementRequestCount();

        try {
            var coins = coinChangeServiceFactory.getCoinChangeService().calculateMinCoinChange(request);
            logger.info("Successfully calculated coins: {}", coins);
            return ResponseEntity.ok(new CoinChangeResponse(coins));
        } finally {
            long duration = System.nanoTime() - startTime;
            requestMetrics.recordRequestDuration(duration, TimeUnit.NANOSECONDS);
        }
    }
}
