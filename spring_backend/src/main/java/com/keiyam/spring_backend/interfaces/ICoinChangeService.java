package com.keiyam.spring_backend.interfaces;

import com.keiyam.spring_backend.dto.CoinChangeRequest;

import java.util.List;

public interface ICoinChangeService {

    List<Double> calculateMinCoinChange(CoinChangeRequest request);
}
