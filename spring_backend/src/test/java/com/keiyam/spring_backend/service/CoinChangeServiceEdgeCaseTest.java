package com.keiyam.spring_backend.service;

import com.keiyam.spring_backend.dto.CoinChangeRequest;
import com.keiyam.spring_backend.interfaces.ICoinChangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeServiceEdgeCaseTest {

    private ICoinChangeService coinChangeService;

    @BeforeEach
    void setUp() {
        coinChangeService = new CoinChangeService();
    }

    @Test
    void testCalculateMinCoinChange_ExactMatch() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(new BigDecimal("10.00"));
        request.setDenominations(Arrays.asList(
                new BigDecimal("0.01"),
                new BigDecimal("0.05"),
                new BigDecimal("0.1"),
                new BigDecimal("0.25"),
                new BigDecimal("1"),
                new BigDecimal("5"),
                new BigDecimal("10")
        ));

        List<Double> result = coinChangeService.calculateMinCoinChange(request);

        assertEquals(Arrays.asList(10.0), result);
    }

    @Test
    void testCalculateMinCoinChange_MinAmount() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(BigDecimal.ZERO);
        request.setDenominations(Arrays.asList(
                new BigDecimal("0.01"),
                new BigDecimal("0.05"),
                new BigDecimal("0.1"),
                new BigDecimal("0.25"),
                new BigDecimal("1")
        ));

        List<Double> result = coinChangeService.calculateMinCoinChange(request);

        assertTrue(result.isEmpty());
    }

    @Test
    void testCalculateMinCoinChange_MaxAmount() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(new BigDecimal("10000.00"));
        request.setDenominations(Arrays.asList(
                new BigDecimal("0.01"),
                new BigDecimal("0.05"),
                new BigDecimal("0.1"),
                new BigDecimal("0.25"),
                new BigDecimal("1"),
                new BigDecimal("5"),
                new BigDecimal("10"),
                new BigDecimal("50"),
                new BigDecimal("100"),
                new BigDecimal("1000")
        ));

        List<Double> result = coinChangeService.calculateMinCoinChange(request);
        assertEquals(Arrays.asList(1000.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0), result);

        assertFalse(result.isEmpty());
    }
}
