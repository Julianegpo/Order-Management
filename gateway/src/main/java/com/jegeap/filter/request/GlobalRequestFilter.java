package com.jegeap.filter.request;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.net.HttpHeaders;
import com.jegeap.util.JwtUtil;

import reactor.core.publisher.Mono;

/* 
 * Pre GlobalFilter
 */
@Component
public class GlobalRequestFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // free access to path for requesting token
        if ("/api/auth/token".equals(request.getPath().toString())) {
            return chain.filter(exchange);
        } else {
            // check if token is present
            if (request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                // verify token
                String token = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                boolean isTokenValid = JwtUtil.validateToken(token);
                if (isTokenValid) {
                    return chain.filter(exchange);
                } else {
                    // return 401
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            } else {
                // return 401
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }
    }

    /* 
     * a() (Order -1) is executed first in the “pre” phase and last in the “post” phase.
     * b() (Order 0) is executed next in the “pre” phase and next-to-last in the “post” phase.
     * c() (Order 1) is executed last in the “pre” phase and first in the “post” phase.
     * We use -2 to be top prio
     */
    @Override
    public int getOrder() {
        return -2;
    }

}
