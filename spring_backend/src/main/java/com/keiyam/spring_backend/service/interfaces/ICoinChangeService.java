package com.keiyam.spring_backend.service.interfaces;

import com.keiyam.spring_backend.dto.CoinChangeRequest;

import java.math.BigDecimal;
import java.util.Deque;

public interface ICoinChangeService {

    Deque<BigDecimal> calculateMinCoinChange(CoinChangeRequest request);
}
