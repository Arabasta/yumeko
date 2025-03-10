package com.keiyam.spring_backend.service;

import com.keiyam.spring_backend.dto.CoinChangeRequest;
import com.keiyam.spring_backend.service.interfaces.ICoinChangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CoinChangeServiceTest {

    private ICoinChangeService coinChangeService;

    @BeforeEach
    void setUp() {
        coinChangeService = new GreedyCoinChangeService();
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

        Deque<BigDecimal> result = coinChangeService.calculateMinCoinChange(request);

        Deque<BigDecimal> expected = new ArrayDeque<>();
        expected.add(new BigDecimal("0.25"));
        expected.add(new BigDecimal("1.00"));
        expected.add(new BigDecimal("5.00"));
        expected.add(new BigDecimal("5.00"));

        assertEquals(expected, result);
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

        Deque<BigDecimal> result = coinChangeService.calculateMinCoinChange(request);

        Deque<BigDecimal> expected = new ArrayDeque<>();
        expected.add(new BigDecimal("0.01"));
        expected.add(new BigDecimal("0.01"));

        assertEquals(expected, result);
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

        Deque<BigDecimal> result = coinChangeService.calculateMinCoinChange(request);

        Deque<BigDecimal> expected = new ArrayDeque<>();
        expected.add(new BigDecimal("0.01"));
        expected.add(new BigDecimal("0.01"));
        expected.add(new BigDecimal("0.01"));
        expected.add(new BigDecimal("0.01"));
        expected.add(new BigDecimal("0.25"));
        expected.add(new BigDecimal("0.25"));
        expected.add(new BigDecimal("1.00"));
        expected.add(new BigDecimal("5.00"));
        expected.add(new BigDecimal("10.00"));
        expected.add(new BigDecimal("10.00"));
        expected.add(new BigDecimal("50.00"));
        expected.add(new BigDecimal("100.00"));
        expected.add(new BigDecimal("100.00"));
        expected.add(new BigDecimal("100.00"));
        expected.add(new BigDecimal("100.00"));
        expected.add(new BigDecimal("100.00"));
        expected.add(new BigDecimal("100.00"));
        expected.add(new BigDecimal("100.00"));
        expected.add(new BigDecimal("1000.00"));
        expected.add(new BigDecimal("1000.00"));
        expected.add(new BigDecimal("1000.00"));
        expected.add(new BigDecimal("1000.00"));
        expected.add(new BigDecimal("1000.00"));
        expected.add(new BigDecimal("1000.00"));
        expected.add(new BigDecimal("1000.00"));
        expected.add(new BigDecimal("1000.00"));

        assertEquals(expected, result);
        assertFalse(result.isEmpty());
    }
}
