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
 * TODO: test this, use big decimal
 */
@Service
public class DPCoinChangeService extends AbstractCoinChangeService {

    private static final Logger logger = Logger.getLogger(DPCoinChangeService.class.getName());

    @Override
    public Deque<BigDecimal> calculateMinCoinChange(CoinChangeRequest request) {
        logger.info("Calculating minimum coins for amount: " + request.getAmount());
        BigDecimal amount = request.getAmount();
        List<BigDecimal> denominations = request.getDenominations();

        sortDenominationsIfNeeded(denominations);

        Deque<BigDecimal> result = initDequeWithMinCapacity(denominations, amount);

        // convert to int
        // todo: very  messy
        int amountInCents = amount.multiply(BigDecimal.valueOf(100)).intValue();
        int[] denominationsInCents = denominations.stream()
                .mapToInt(d -> d.multiply(BigDecimal.valueOf(100)).intValue())
                .toArray();

        // Calculate the minimum number of coins using DP
        int[] dp = initializeDPArray(amountInCents);
        int[] lastCoinUsed = new int[amountInCents + 1];

        fillDPArray(denominationsInCents, dp, lastCoinUsed, amountInCents);

        if (dp[amountInCents] == Integer.MAX_VALUE) {
            throw new InvalidCoinChangeRequestException("Cannot make the exact amount with the given denominations.");
        }

        // backtrack
        calculateCoins(amountInCents, lastCoinUsed, result);
        logger.info("Calculated minimum coins: " + result);
        return result;
    }

    /**
     * Initializes the DP array with a large value (infinity) and sets the base case.
     *
     * @param amountInCents the amount in cents
     * @return the initialized DP array
     */
    private int[] initializeDPArray(int amountInCents) {
        int[] dp = new int[amountInCents + 1];
        for (int i = 1; i <= amountInCents; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0; // Base case: 0 coins are needed to make amount 0
        return dp;
    }

    /**
     * Fills the DP array using the given denominations.
     *
     * @param denominationsInCents the list of denominations in cents
     * @param dp                   the DP array to fill
     * @param lastCoinUsed         the array to store the last coin used for each amount
     * @param amountInCents        the amount in cents
     */
    private void fillDPArray(int[] denominationsInCents, int[] dp, int[] lastCoinUsed, int amountInCents) {
        for (int coin : denominationsInCents) {
            for (int i = coin; i <= amountInCents; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                    lastCoinUsed[i] = coin;
                }
            }
        }
    }

    /**
     * Backtracks to find the coins used and adds them to the result deque.
     *
     * @param amountInCents the amount in cents
     * @param lastCoinUsed  the array storing the last coin used for each amount
     * @param result        the deque to store the resulting coins
     */
    private void calculateCoins(int amountInCents, int[] lastCoinUsed, Deque<BigDecimal> result) {
        int remainingAmount = amountInCents;
        while (remainingAmount > 0) {
            int coin = lastCoinUsed[remainingAmount];
            addCoinsToResult(BigDecimal.valueOf(coin / 100.0), 1, result);
            remainingAmount -= coin;
        }
    }
}
