package com.keiyam.spring_backend.controller;

import com.keiyam.spring_backend.dto.CoinChangeRequest;
import com.keiyam.spring_backend.dto.CoinChangeResponse;
import com.keiyam.spring_backend.service.CoinChangeServiceFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling coin change requests.
 */
@RestController
@RequestMapping("/api/v1/coin-change")
@RequiredArgsConstructor
public class CoinChangeControllerV1 {
    private static final Logger logger = LoggerFactory.getLogger(CoinChangeControllerV1.class);
    private final CoinChangeServiceFactory coinChangeServiceFactory;

    /**
     * Endpoint to get the minimum number of coins needed to make up the given amount.
     *
     * @param request the request containing the amount and denominations
     * @return a ResponseEntity containing the list of coins in ascending order
     */
    @PostMapping("minimum-coins")
    public ResponseEntity<CoinChangeResponse> getMinimumCoins(@Valid @RequestBody CoinChangeRequest request) {
        logger.info("Received request to calculate minimum coins for amount: {}", request.getAmount());
        var coins = coinChangeServiceFactory.getCoinChangeService().calculateMinCoinChange(request);
        logger.info("Successfully calculated coins: {}", coins);
        return ResponseEntity.ok(new CoinChangeResponse(coins));
    }
}
