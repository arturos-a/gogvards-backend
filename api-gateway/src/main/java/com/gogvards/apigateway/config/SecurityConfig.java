package com.gogvards.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Value("${gateway.auth-header}")
    private String authHeaderName;


    public WebFilter postGlobalFilter() {
        return (exchange, chain) -> {
            Mono<Void> monoFilter = ReactiveSecurityContextHolder.getContext().map(sc -> sc.getAuthentication())
                    .flatMap(authentication -> {
                        StringBuilder authHeader = new StringBuilder();
//                        if (authentication.getPrincipal() instanceof AttestationOidcUser) {
//                            AccessToken accessToken = accessTokenGenerator.getAccessData(authentication.getName());
//                            authHeader.append(tokenDataConverter.encodeBase64(accessToken));
//                        }
                        return chain.filter
                                (
                                        exchange.mutate().request(i ->
                                                i.header(authHeaderName, authHeader.toString()).build()
                                        ).build()
                                );
                    });
            return monoFilter;
        };
    }

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(
                authorizeExchangeSpec -> authorizeExchangeSpec.anyExchange().authenticated()
        ).csrf(csrfSpec -> csrfSpec.disable())//.addFilterBefore(postGlobalFilter(), HTTP_HEADERS_WRITER)
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
