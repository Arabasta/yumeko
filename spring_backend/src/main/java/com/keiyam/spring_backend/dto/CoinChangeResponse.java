package com.keiyam.spring_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Deque;

@Getter
@Setter
@AllArgsConstructor
public class CoinChangeResponse {
    private Deque<BigDecimal> coins;
}
