package com.keiyam.spring_backend.service;

import com.keiyam.spring_backend.dto.CoinChangeRequest;
import com.keiyam.spring_backend.exception.InvalidCoinChangeRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Service for calculating the minimum number of coins needed to make up a given amount.
 */
@Service
public class DPCoinChangeService extends AbstractCoinChangeService {

    @Override
    public Deque<BigDecimal> calculateMinCoinChange(CoinChangeRequest request) {
        BigDecimal amount = request.getAmount();
        List<BigDecimal> denominations = request.getDenominations();
        Deque<BigDecimal> result = new ArrayDeque<>();

        // TODO

        if (amount.compareTo(BigDecimal.ZERO) != 0) {
            throw new InvalidCoinChangeRequestException("Cannot make the exact amount with the given denominations.");
        }

        return result;
    }
}
