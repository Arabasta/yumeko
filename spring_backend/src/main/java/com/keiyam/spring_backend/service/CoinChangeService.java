package com.keiyam.spring_backend.service;

import com.keiyam.spring_backend.dto.CoinChangeRequest;
import com.keiyam.spring_backend.exception.InvalidCoinChangeRequestException;
import com.keiyam.spring_backend.service.interfaces.ICoinChangeService;
import com.keiyam.spring_backend.util.ListUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Service for calculating the minimum number of coins needed to make up a given amount.
 */
@Service
public class CoinChangeService implements ICoinChangeService {

    /**
     * Calculates the minimum number of coins needed to make up the given amount using the provided denominations.
     *
     * @param request the request containing the total amount and denominations
     * @return a list of coins that make up the amount in ascending order
     * @throws InvalidCoinChangeRequestException if the exact amount cannot be made with the given denominations
     */
    @Override
    public Deque<BigDecimal> calculateMinCoinChange(CoinChangeRequest request) {
        BigDecimal amount = request.getAmount();
        List<BigDecimal> denominations = request.getDenominations();
        Deque<BigDecimal> result = new ArrayDeque<>();

        sortDenominationsIfNeeded(denominations);

        amount = calculateCoins(amount, denominations, result);

        if (amount.compareTo(BigDecimal.ZERO) != 0) {
            throw new InvalidCoinChangeRequestException("Cannot make the exact amount with the given denominations.");
        }

        return result;
    }

    /**
     * Sorts the denominations in ascending order if they are not already sorted.
     * Time complexity: O(n)
     *                  O(n log n) if sorting is needed
     *
     * @param denominations the list of denominations to check and sort
     */
    private void sortDenominationsIfNeeded(List<BigDecimal> denominations) {
        if (!ListUtil.isSortedAscending(denominations)) {
            Collections.sort(denominations);
        }
    }

    /**
     * Calculates the minimum number of coins needed and adds them to the result list.
     * Time complexity: O(n)
     *
     * @param amount        the amount to be changed
     * @param denominations the list of available denominations
     * @param result        the list to store the resulting coins
     * @return the remaining amount after calculating the minimum number of coins
     */
    private BigDecimal calculateCoins(BigDecimal amount, List<BigDecimal> denominations, Deque<BigDecimal> result) {
        for (int i = denominations.size() - 1; i >= 0; i--) {
            BigDecimal currentDenomination = denominations.get(i);

            // mod amount by current denomination
            int numCoins = amount.divide(currentDenomination, RoundingMode.DOWN).intValue();
            amount = amount.remainder(currentDenomination);

            addCoinsToResult(currentDenomination, numCoins, result);
        }
        return amount;
    }

    /**
     * Adds the number of coins to deque
     * Time complexity: O(n)
     *
     * @param denomination the coin denomination to add
     * @param numCoins     the number of coins to add
     * @param result       the list to store the resulting coins
     */
    private void addCoinsToResult(BigDecimal denomination, int numCoins, Deque<BigDecimal> result) {
        for (int i = 0; i < numCoins; i++) {
            result.addFirst(denomination); // add to head so sorting in ascending isn't needed
        }
    }
}
