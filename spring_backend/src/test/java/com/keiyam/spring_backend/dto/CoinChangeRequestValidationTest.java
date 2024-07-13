package com.keiyam.spring_backend.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CoinChangeRequestValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    void testValidCoinChangeRequest() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(new BigDecimal("10.00"));
        request.setDenominations(Arrays.asList(
                BigDecimal.valueOf(0.01),
                BigDecimal.valueOf(0.05),
                BigDecimal.valueOf(0.1),
                BigDecimal.valueOf(0.2),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(10)
        ));

        Set<ConstraintViolation<CoinChangeRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testNullAmount() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(null);
        request.setDenominations(Arrays.asList(
                BigDecimal.valueOf(0.01),
                BigDecimal.valueOf(0.05),
                BigDecimal.valueOf(0.1),
                BigDecimal.valueOf(0.2),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(10)
        ));

        Set<ConstraintViolation<CoinChangeRequest>> violations = validator.validate(request);

        assertEquals(1, violations.size());
        assertEquals("Amount cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    void testNegativeAmount() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(new BigDecimal("-1.00"));
        request.setDenominations(Arrays.asList(
                BigDecimal.valueOf(0.01),
                BigDecimal.valueOf(0.05),
                BigDecimal.valueOf(0.1),
                BigDecimal.valueOf(0.2),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(10)
        ));

        Set<ConstraintViolation<CoinChangeRequest>> violations = validator.validate(request);

        assertEquals(1, violations.size());
        assertEquals("Amount must be greater than or equal to 0", violations.iterator().next().getMessage());
    }

    @Test
    void testAmountExceedsMax() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(new BigDecimal("10000.01"));
        request.setDenominations(Arrays.asList(
                BigDecimal.valueOf(0.01),
                BigDecimal.valueOf(0.05),
                BigDecimal.valueOf(0.1),
                BigDecimal.valueOf(0.2),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(10)
        ));

        Set<ConstraintViolation<CoinChangeRequest>> violations = validator.validate(request);

        assertEquals(1, violations.size());
        assertEquals("Amount must be less than or equal to 10000.00", violations.iterator().next().getMessage());
    }

    @Test
    void testNullDenominations() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(new BigDecimal("10.00"));
        request.setDenominations(null);

        Set<ConstraintViolation<CoinChangeRequest>> violations = validator.validate(request);

        assertEquals(1, violations.size());
        assertEquals("Denominations cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidDenominations() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(new BigDecimal("10.00"));
        request.setDenominations(Arrays.asList(
                BigDecimal.valueOf(0.03),
                BigDecimal.valueOf(0.07),
                BigDecimal.valueOf(0.11),
                BigDecimal.valueOf(0.21)
        ));

        Set<ConstraintViolation<CoinChangeRequest>> violations = validator.validate(request);

        assertEquals(1, violations.size());
        assertEquals("Invalid coin denominations", violations.iterator().next().getMessage());
    }

    @Test
    void testMixedValidAndInvalidDenominations() {
        CoinChangeRequest request = new CoinChangeRequest();
        request.setAmount(new BigDecimal("10.00"));
        request.setDenominations(Arrays.asList(
                BigDecimal.valueOf(0.01),
                BigDecimal.valueOf(0.03),
                BigDecimal.valueOf(0.05),
                BigDecimal.valueOf(0.07)
        ));

        Set<ConstraintViolation<CoinChangeRequest>> violations = validator.validate(request);

        assertEquals(1, violations.size());
        assertEquals("Invalid coin denominations", violations.iterator().next().getMessage());
    }
}
