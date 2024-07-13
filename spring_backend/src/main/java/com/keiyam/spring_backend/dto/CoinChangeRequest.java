package com.keiyam.spring_backend.dto;

import com.keiyam.spring_backend.annotation.ValidDenominations;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CoinChangeRequest {

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0", message = "Amount must be greater than or equal to 0")
    @DecimalMax(value = "10000.00", message = "Amount must be less than or equal to 10000.00")
    private BigDecimal amount;

    @NotNull(message = "Denominations cannot be null")
    @ValidDenominations
    private List<BigDecimal> denominations;
}
