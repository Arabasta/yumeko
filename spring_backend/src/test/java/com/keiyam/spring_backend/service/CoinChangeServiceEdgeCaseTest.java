package com.keiyam.spring_backend.service;

import com.keiyam.spring_backend.dto.CoinChangeRequest;
import com.keiyam.spring_backend.service.interfaces.ICoinChangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

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

        Deque<BigDecimal> result = coinChangeService.calculateMinCoinChange(request);

        Deque<BigDecimal> expected = new ArrayDeque<>();
        expected.add(new BigDecimal("10.00"));

        assertEquals(expected, result);
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

        Deque<BigDecimal> result = coinChangeService.calculateMinCoinChange(request);

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

        Deque<BigDecimal> result = coinChangeService.calculateMinCoinChange(request);

        Deque<BigDecimal> expected = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            expected.add(new BigDecimal("1000.00"));
        }

        assertEquals(expected, result);
        assertFalse(result.isEmpty());
    }
}