package com.keiyam.spring_backend.validator;

import com.keiyam.spring_backend.annotation.ValidDenominations;
import com.keiyam.spring_backend.config.DenominationConfig;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

/**
 * Validator for checking if the provided denominations are valid.
 */
@Component
@RequiredArgsConstructor
public class DenominationValidator implements
        ConstraintValidator<ValidDenominations, List<BigDecimal>> {

    private final DenominationConfig denominationConfig;

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

        return new HashSet<>(denominationConfig.getDenominations()).containsAll(denominations);
    }

}
