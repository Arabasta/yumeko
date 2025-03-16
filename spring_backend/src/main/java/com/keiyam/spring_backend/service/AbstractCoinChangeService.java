package com.keiyam.spring_backend.service;

import com.keiyam.spring_backend.dto.CoinChangeRequest;
import com.keiyam.spring_backend.util.ListUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public abstract class AbstractCoinChangeService {
    public abstract Deque<BigDecimal> calculateMinCoinChange(CoinChangeRequest request);

    /**
     * Sorts the denominations in ascending order if they are not already sorted.
     * Time complexity: O(n)
     *                  O(n log n) if sorting is needed
     *
     * @param denominations the list of denominations to check and sort
     */
    protected static void sortDenominationsIfNeeded(List<BigDecimal> denominations) {
        if (!ListUtil.isSortedAscending(denominations)) {
            Collections.sort(denominations);
        }
    }

    /**
     * Initializes the result deque with the minimum required capacity.
     *
     * @param denominations the list of denominations
     * @param amount        the amount to be changed
     * @return a new ArrayDeque with the minimum required capacity
     */
    protected static ArrayDeque<BigDecimal> initDequeWithMinCapacity(List<BigDecimal> denominations, BigDecimal amount) {
        BigDecimal largestDenomination = denominations.get(denominations.size() - 1);
        int minCapacityRequired = amount.divide(largestDenomination, RoundingMode.DOWN).intValue();
        return new ArrayDeque<>(minCapacityRequired);
    }

    /**
     * Adds the number of coins to deque
     *
     * @param denomination the coin denomination to add
     * @param numCoins     the number of coins to add
     * @param result       the list to store the resulting coins
     */
    protected static void addCoinsToResult(BigDecimal denomination, int numCoins, Deque<BigDecimal> result) {
        for (int i = 0; i < numCoins; i++) {
            result.addFirst(denomination); // add to head so sorting in ascending isn't needed
        }
    }
}
