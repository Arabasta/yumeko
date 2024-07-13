package com.keiyam.spring_backend.service;

import com.keiyam.spring_backend.dto.CoinChangeRequest;
import com.keiyam.spring_backend.exception.InvalidCoinChangeRequestException;
import com.keiyam.spring_backend.interfaces.ICoinChangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoinChangeServiceInvalidInputTest {

    private ICoinChangeService coinChangeService;

    @BeforeEach
    void setUp() {
        coinChangeService = new CoinChangeService();
    }

    @Test
    void testCalculateMinCoinChange_CannotMakeExactAmount() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(new BigDecimal("3.33"));
        request.setDenominations(Arrays.asList(
                new BigDecimal("0.1"),
                new BigDecimal("0.25"),
                new BigDecimal("1"),
                new BigDecimal("2")
        ));

        InvalidCoinChangeRequestException exception = assertThrows(
                InvalidCoinChangeRequestException.class,
                () -> coinChangeService.calculateMinCoinChange(request)
        );

        assertEquals("Cannot make the exact amount with the given denominations.", exception.getMessage());
    }

    @Test
    void testCalculateMinCoinChange_EmptyDenominations() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(new BigDecimal("10.00"));
        request.setDenominations(Arrays.asList());

        InvalidCoinChangeRequestException exception = assertThrows(
                InvalidCoinChangeRequestException.class,
                () -> coinChangeService.calculateMinCoinChange(request)
        );

        assertEquals("Cannot make the exact amount with the given denominations.", exception.getMessage());
    }

}
