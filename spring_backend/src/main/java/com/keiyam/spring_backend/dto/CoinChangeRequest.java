package com.keiyam.spring_backend.dto;

import com.keiyam.spring_backend.validator.annotation.ValidDenominations;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinChangeRequest {

    @Schema(description = "The amount for which to calculate minimum coins", example = "123.45")
    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0", message = "Amount must be greater than or equal to 0")
    @DecimalMax(value = "10000.00", message = "Amount must be less than or equal to 10000.00")
    private BigDecimal amount;

    @Schema(description = "List of coin denominations", example = "[1, 5, 10, 25]")
    @NotNull(message = "Denominations cannot be null")
    @ValidDenominations
    private List<BigDecimal> denominations;
}
