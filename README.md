# React | Springboot | Docker | Redis | Prometheus | AWS | Github Actions

## Background

Originally for an internship challenge, expanded on this project by adding a bunch of new stuff.

---

## Deployment

##### Frontend

Frontend is deployed to S3 using GitHub Actions. Workflow is defined in `.github/workflows/cd_react_frontend.yml`.

##### Backend

Workflow update in progress...

---

## Running the Project

On aarch64 (Apple Silicon Macs)

```bash
chmod +x docker-buildx-aarch64.sh 
./docker-buildx-aarch64.sh
docker compose up
```

---

## API
Swagger Documentation: http://localhost:8080/swagger-ui.html

api-docs.yml: https://github.com/Arabasta/Mini-Coin/blob/master/api-docs.yaml

#### Request

Request endpoint: `http://localhost:8080/api/v1/coin-change`

```json
{
    "amount": 7.01,
    "denominations": [0.1, 0.5, 0.01, 1, 5, 10]
}
```

#### Response

The API will respond with the minimum number of coins needed to make the given amount.

```json
{
  "coins": [
    0.01,
    1.0,
    1.0,
    5.0
  ]
}
```

---

# Cache
Redis is used for caching the results of the coin change calculation. Cache is evicted when DenominationsConfig.setDenominations() is called.
Docker compose file will run the Redis instance.

Sample logs:
```
spring-backend-1  | 2025-03-10T19:45:49.024Z  INFO 1 --- [spring_backend] [nio-8080-exec-1] c.k.s.controller.CoinChangeControllerV1  : Received request to calculate minimum coins for amount: 10
spring-backend-1  | 2025-03-10T19:45:49.052Z TRACE 1 --- [spring_backend] [nio-8080-exec-1] o.s.cache.interceptor.CacheInterceptor   : Computed cache key '10-341' for operation Builder[public java.util.Deque com.keiyam.spring_backend.service.GreedyCoinChangeService.calculateMinCoinChange(com.keiyam.spring_backend.dto.CoinChangeRequest)] caches=[coinChangeResults] | key='#request.amount.toString() + '-' + #request.denominations.hashCode()' | keyGenerator='' | cacheManager='' | cacheResolver='' | condition='' | unless='' | sync='false'
spring-backend-1  | 2025-03-10T19:45:50.254Z TRACE 1 --- [spring_backend] [nio-8080-exec-1] o.s.cache.interceptor.CacheInterceptor   : No cache entry for key '10-341' in cache(s) [coinChangeResults]
spring-backend-1  | 2025-03-10T19:45:50.256Z  INFO 1 --- [spring_backend] [nio-8080-exec-1] c.k.s.service.GreedyCoinChangeService    : Calculating minimum coins for amount: 10
spring-backend-1  | 2025-03-10T19:45:50.277Z  INFO 1 --- [spring_backend] [nio-8080-exec-1] c.k.s.service.GreedyCoinChangeService    : Calculated minimum coins: [10]
spring-backend-1  | 2025-03-10T19:45:50.278Z TRACE 1 --- [spring_backend] [nio-8080-exec-1] o.s.cache.interceptor.CacheInterceptor   : Creating cache entry for key '10-341' in cache(s) [coinChangeResults]
spring-backend-1  | 2025-03-10T19:45:50.317Z  INFO 1 --- [spring_backend] [nio-8080-exec-1] c.k.s.controller.CoinChangeControllerV1  : Successfully calculated coins: [10]
spring-backend-1  | 2025-03-10T19:46:13.332Z  INFO 1 --- [spring_backend] [nio-8080-exec-3] c.k.s.controller.CoinChangeControllerV1  : Received request to calculate minimum coins for amount: 10
spring-backend-1  | 2025-03-10T19:46:13.375Z TRACE 1 --- [spring_backend] [nio-8080-exec-3] o.s.cache.interceptor.CacheInterceptor   : Computed cache key '10-341' for operation Builder[public java.util.Deque com.keiyam.spring_backend.service.GreedyCoinChangeService.calculateMinCoinChange(com.keiyam.spring_backend.dto.CoinChangeRequest)] caches=[coinChangeResults] | key='#request.amount.toString() + '-' + #request.denominations.hashCode()' | keyGenerator='' | cacheManager='' | cacheResolver='' | condition='' | unless='' | sync='false'
spring-backend-1  | 2025-03-10T19:46:13.756Z TRACE 1 --- [spring_backend] [nio-8080-exec-3] o.s.cache.interceptor.CacheInterceptor   : Cache entry for key '10-341' found in cache(s) [coinChangeResults]
spring-backend-1  | 2025-03-10T19:46:13.757Z  INFO 1 --- [spring_backend] [nio-8080-exec-3] c.k.s.controller.CoinChangeControllerV1  : Successfully calculated coins: [10]
```

# Metrics
## Prometheus
URL: http://localhost:9090

**Metrics:**

Total requests:

`coin_change_requests_total`

Request duration:

`rate(coin_change_request_duration_seconds_sum[1m]) / rate(coin_change_request_duration_seconds_count[1m])`


## Grafana
URL: http://localhost:3000

Credentials: admin/admin

1. Add Prometheus as a data source: http://prometheus:9090
2. Create dashboard


