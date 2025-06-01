package com.keiyam.spring_backend.service;

import com.keiyam.spring_backend.dto.CoinChangeRequest;
import com.keiyam.spring_backend.exception.InvalidCoinChangeRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.List;
import java.util.logging.Logger;

/**
 * Service for calculating the minimum number of coins needed to make up a given amount.
 */
@Service
public class DPCoinChangeService extends AbstractCoinChangeService {

    private static final Logger logger = Logger.getLogger(DPCoinChangeService.class.getName());

    @Override
    public Deque<BigDecimal> calculateMinCoinChange(CoinChangeRequest request) {
        // todo: implement
    }

}
