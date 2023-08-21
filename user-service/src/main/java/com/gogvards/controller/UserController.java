package com.gogvards.controller;

import com.gogvards.dto.UserInfo;
import com.gogvards.service.UserService;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class UserController {
    @Value("${gateway.auth-header}")
    private String authHeaderName;
    @Inject
    private UserService userService;

    @Get("api/userinfo")
    public Mono<UserInfo> getUserInfo(HttpHeaders headers) {
        String uuid = headers.get(authHeaderName);
        return userService.getUserInfo(uuid);
    }
}
