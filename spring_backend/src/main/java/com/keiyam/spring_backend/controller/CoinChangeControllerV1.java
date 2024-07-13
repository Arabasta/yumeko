package com.keiyam.spring_backend.controller;

import com.keiyam.spring_backend.dto.CoinChangeRequest;
import com.keiyam.spring_backend.dto.CoinChangeResponse;
import com.keiyam.spring_backend.service.CoinChangeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for handling coin change requests.
 */
@RestController
@RequestMapping("/api/v1/coin-change")
public class CoinChangeControllerV1 {

    private final CoinChangeService coinChangeService;

    @Autowired
    public CoinChangeControllerV1(CoinChangeService coinChangeService) {
        this.coinChangeService = coinChangeService;
    }

    /**
     * Endpoint to get the minimum number of coins needed to make up the given amount.
     *
     * @param request the request containing the amount and denominations
     * @return a ResponseEntity containing the list of coins in ascending order
     */
    @PostMapping
    public ResponseEntity<CoinChangeResponse> getMinimumCoins(@Valid @RequestBody CoinChangeRequest request) {
        List<Double> coins = coinChangeService.calculateMinCoinChange(request);

        return ResponseEntity.ok(new CoinChangeResponse(coins));
    }
}
