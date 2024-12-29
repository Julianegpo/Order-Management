package com.jegeap.filter.response;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/* 
 * Post GlobalFilter
 */
@Component
public class GlobalResponseFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(
            Mono.fromRunnable(() -> {
                exchange.mutate().response(exchange.getResponse()).build();
            })
        );
    }

    @Override
    public int getOrder() {
        // throw new UnsupportedOperationException("Unimplemented method 'getOrder'");
        return 0;
    }

}
