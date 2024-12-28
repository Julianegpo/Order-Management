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
        ServerHttpResponse request = exchange.getResponse();
        System.out.println("GlobalResponseFilter: " + request.toString());
        return chain.filter(exchange).then(
            Mono.fromRunnable(() -> {
                    System.out.println("GlobalRequestFilter: " + exchange.toString());
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
