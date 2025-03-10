package com.keiyam.spring_backend.service;

import com.keiyam.spring_backend.config.DenominationConfig;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Factory instead of strategy pattern cause algo is only set once on startup
 */
@Service
@Getter
public class CoinChangeServiceFactory {
    private static final Logger logger = LoggerFactory.getLogger(CoinChangeServiceFactory.class);
    private final AbstractCoinChangeService coinChangeService;
    private final DenominationConfig denominationConfig;

    @Autowired
    public CoinChangeServiceFactory(DenominationConfig denominationConfig, GreedyCoinChangeService greedyCoinChangeService,
                                    DPCoinChangeService dpCoinChangeService) {
        this.denominationConfig = denominationConfig;
        if (isDenominationsValidForGreedy()) {
            logger.info("Using GreedyCoinChangeService");
            this.coinChangeService = greedyCoinChangeService;
        } else {
            logger.info("Using DPCoinChangeService");
            this.coinChangeService = dpCoinChangeService;
        }
    }

    private boolean isDenominationsValidForGreedy() {
        List<BigDecimal> denoms = new ArrayList<>(denominationConfig.getDenominations());
        denoms.sort(Comparator.naturalOrder());

        for (int i = 1; i < denoms.size(); i++) {
            BigDecimal prev = denoms.get(i - 1);
            BigDecimal curr = denoms.get(i);
            BigDecimal twicePrev = prev.multiply(BigDecimal.valueOf(2));

            // if curr < 2 * prev
            if (curr.compareTo(twicePrev) < 0) {
                return false;
            }
        }
        return true;
    }
}
