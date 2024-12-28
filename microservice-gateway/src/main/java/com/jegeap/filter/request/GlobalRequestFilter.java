package com.jegeap.filter.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.jegeap.util.JwtUtil;

import reactor.core.publisher.Mono;

/* 
 * Pre GlobalFilter
 */
@Component
public class GlobalRequestFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // free access to path for requesting token
        if ("/api/auth/token".equals(request.getPath().toString())) {
            return chain.filter(exchange);
        } else {
            // check if token is present
            if (request.getHeaders().containsKey("Authorization")) {
                // verify token
                String token = request.getHeaders().get("Authorization").get(0);
                boolean isTokenValid = jwtUtil.validateToken(token) != null;
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

    @Override
    public int getOrder() {
        // throw new UnsupportedOperationException("Unimplemented method 'getOrder'");
        return -2;
    }

}
