package com.keiyam.spring_backend.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DenominationValidatorTest {

    private DenominationValidator validator;

    @BeforeEach
    void setUp() {
        validator = new DenominationValidator();
    }

    @Test
    void testValidDenominations() {
        List<BigDecimal> validDenominations = Arrays.asList(
                BigDecimal.valueOf(0.01),
                BigDecimal.valueOf(0.05),
                BigDecimal.valueOf(0.1),
                BigDecimal.valueOf(0.2),
                BigDecimal.valueOf(0.5),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(1000)
        );
        assertTrue(validator.isValid(validDenominations, null));
    }

    @Test
    void testInvalidDenominations() {
        List<BigDecimal> invalidDenominations = Arrays.asList(
                BigDecimal.valueOf(0.03),
                BigDecimal.valueOf(0.07),
                BigDecimal.valueOf(0.11),
                BigDecimal.valueOf(0.21)
        );
        assertFalse(validator.isValid(invalidDenominations, null));
    }

    @Test
    void testMixedDenominations() {
        List<BigDecimal> mixedDenominations = Arrays.asList(
                BigDecimal.valueOf(0.01),
                BigDecimal.valueOf(0.03),
                BigDecimal.valueOf(0.05),
                BigDecimal.valueOf(0.07),
                BigDecimal.valueOf(0.1),
                BigDecimal.valueOf(0.11)
        );
        assertFalse(validator.isValid(mixedDenominations, null));
    }

    @Test
    void testNullDenominations() {
        assertTrue(validator.isValid(null, null));
    }
}
