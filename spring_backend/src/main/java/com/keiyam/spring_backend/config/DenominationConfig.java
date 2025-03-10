package com.keiyam.spring_backend.config;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Configuration
@ConfigurationProperties(prefix = "valid-denominations")
public class DenominationConfig {
    private static final Logger logger = LoggerFactory.getLogger(DenominationConfig.class);

    private List<BigDecimal> denominations;

    public void setDenominations(List<BigDecimal> denominations) {
        this.denominations = denominations;
        logger.info("Loaded denominations: {}", denominations);
    }
}