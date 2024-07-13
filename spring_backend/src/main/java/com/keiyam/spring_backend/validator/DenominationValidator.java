package com.keiyam.spring_backend.validator;

import com.keiyam.spring_backend.annotation.ValidDenominations;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//ref for custom annotations: https://www.baeldung.com/spring-mvc-custom-validator
/**
 * Validator for checking if the provided denominations are valid.
 */
public class DenominationValidator implements
        ConstraintValidator<ValidDenominations, List<BigDecimal>> {

    private static final Set<BigDecimal> VALID_DENOMINATIONS = new HashSet<>(Arrays.asList(
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
    ));

    @Override
    public void initialize(ValidDenominations validDenominations) {
    }

    /**
     * Checks if the list of denominations contains valid values.
     *
     * @param denominations the list of denominations to check
     * @param ctx the context in which the constraint is evaluated
     * @return true if all denominations are valid, false otherwise
     */
    @Override
    public boolean isValid(List<BigDecimal> denominations, ConstraintValidatorContext ctx) {
        if (denominations == null) {
            return true;
        }
        for (BigDecimal denomination : denominations) {
            if (!VALID_DENOMINATIONS.contains(denomination)) {
                return false;
            }
        }
        return true;
    }

}
