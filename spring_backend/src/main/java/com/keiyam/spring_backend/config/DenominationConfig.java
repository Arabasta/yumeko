package com.keiyam.spring_backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "valid-denominations")
public class DenominationConfig {
    private Set<BigDecimal> denominations;
}