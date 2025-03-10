package com.keiyam.spring_backend.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
public class RedisCacheConfig {

    private final static int CACHE_EXPIRATION_MINUTES = 60;
    private final static int COIN_CHANGE_CACHE_EXPIRATION_MINUTES = CACHE_EXPIRATION_MINUTES;

    @Bean
    public org.springframework.data.redis.cache.RedisCacheConfiguration cacheConfiguration() {
        return org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(CACHE_EXPIRATION_MINUTES))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    // customizing the cache manager, useless in this case as there's only 1 thing to cache
    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration("coinChangeResults",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(COIN_CHANGE_CACHE_EXPIRATION_MINUTES)));
    }
}

