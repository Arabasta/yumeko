package com.keiyam.spring_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Deque;

@Getter
@Setter
@AllArgsConstructor
public class CoinChangeResponse {
    @Schema(description = "List of coins in ascending order", example = "[1, 5, 10, 25]")
    private Deque<BigDecimal> coins;
}
