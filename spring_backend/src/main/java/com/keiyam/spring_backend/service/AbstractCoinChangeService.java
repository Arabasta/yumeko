package com.keiyam.spring_backend.service;

import com.keiyam.spring_backend.dto.CoinChangeRequest;
import com.keiyam.spring_backend.util.ListUtil;

import java.math.BigDecimal;
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
    protected void sortDenominationsIfNeeded(List<BigDecimal> denominations) {
        if (!ListUtil.isSortedAscending(denominations)) {
            Collections.sort(denominations);
        }
    }
}
