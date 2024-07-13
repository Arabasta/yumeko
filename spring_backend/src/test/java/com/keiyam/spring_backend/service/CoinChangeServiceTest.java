package com.keiyam.spring_backend.service;

import com.keiyam.spring_backend.dto.CoinChangeRequest;
import com.keiyam.spring_backend.interfaces.ICoinChangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeServiceTest {

    private ICoinChangeService coinChangeService;

    @BeforeEach
    void setUp() {
        coinChangeService = new CoinChangeService();
    }

    @Test
    void testCalculateMinCoinChange_ValidInput() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(new BigDecimal("11.25"));
        request.setDenominations(Arrays.asList(
                new BigDecimal("0.01"),
                new BigDecimal("0.05"),
                new BigDecimal("0.1"),
                new BigDecimal("0.25"),
                new BigDecimal("1"),
                new BigDecimal("5")
        ));

        List<Double> result = coinChangeService.calculateMinCoinChange(request);

        assertEquals(Arrays.asList(0.25, 1.0, 5.0, 5.0), result);
    }

    @Test
    void testCalculateMinCoinChange_SmallAmount() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(new BigDecimal("0.02"));
        request.setDenominations(Arrays.asList(
                new BigDecimal("0.01"),
                new BigDecimal("0.05"),
                new BigDecimal("0.1"),
                new BigDecimal("0.25"),
                new BigDecimal("1")
        ));

        List<Double> result = coinChangeService.calculateMinCoinChange(request);

        assertEquals(Arrays.asList(0.01, 0.01), result);
    }

    @Test
    void testCalculateMinCoinChange_LargeAmount() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(new BigDecimal("9876.54"));
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
        assertEquals(Arrays.asList(0.01, 0.01, 0.01, 0.01, 0.25, 0.25, 1.0, 5.0, 10.0, 10.0, 50.0, 100.0, 100.0,
                100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0), result);

        assertFalse(result.isEmpty());
    }
}
