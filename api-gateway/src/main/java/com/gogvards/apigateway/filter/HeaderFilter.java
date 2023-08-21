package com.gogvards.apigateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class HeaderFilter implements WebFilter {
    @Value("${gateway.auth-header}")
    private String authHeaderName;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        return serverWebExchange.getPrincipal().flatMap(pr -> {
            serverWebExchange.getRequest().mutate().header(authHeaderName, pr.getName());
            return webFilterChain.filter(serverWebExchange);
        });

    }
}
