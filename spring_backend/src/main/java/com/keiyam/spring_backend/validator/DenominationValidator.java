package com.keiyam.spring_backend.validator;

import com.keiyam.spring_backend.annotation.ValidDenominations;
import com.keiyam.spring_backend.config.DenominationConfig;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

//ref for custom annotations: https://www.baeldung.com/spring-mvc-custom-validator
/**
 * Validator for checking if the provided denominations are valid.
 */
public class DenominationValidator implements
        ConstraintValidator<ValidDenominations, List<BigDecimal>> {

    private final DenominationConfig denominationConfig;

    @Autowired
    public DenominationValidator(DenominationConfig denominationConfig) {
        this.denominationConfig = denominationConfig;
    }

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
            if (!denominationConfig.getDenominations().contains(denomination)) {
                return false;
            }
        }
        return true;
    }

}
