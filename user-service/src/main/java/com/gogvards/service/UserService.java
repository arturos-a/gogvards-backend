package com.gogvards.service;

import com.gogvards.dto.UserInfo;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserInfo> getUserInfo(String uuid);

    Mono generate();
}
