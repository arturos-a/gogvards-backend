package com.gogvards.service.impl;

import com.gogvards.model.UserInfo;
import com.gogvards.repository.UserInfoRepository;
import com.gogvards.service.UserService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

@Singleton
public class UserServiceImpl implements UserService {
    @Inject
    private UserInfoRepository userInfoRepository;
    @Inject
    private ModelMapper modelMapper;

    @Override
    public Mono<com.gogvards.dto.UserInfo> getUserInfo(String uuid) {
        return userInfoRepository.findById(UUID.fromString(uuid)).map(i -> {
            com.gogvards.dto.UserInfo map = modelMapper.map(i, com.gogvards.dto.UserInfo.class);
            return map;
        }).switchIfEmpty(Mono.error(new HttpStatusException(HttpStatus.UNAUTHORIZED, "")));
    }

    @Override
    @Transactional
    public Mono<UserInfo> generate() {
        UserInfo userParent = new UserInfo();
        userParent.setFirstName("Гарри");
        userParent.setLastName("Поттер");
        userParent.setMiddleName("Иванович");
        userParent.setUserRole("PARENT");

        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("Иван");
        userInfo.setLastName("Поттер");
        userInfo.setMiddleName("Гариевич");
        userInfo.setUserRole("STUDENT");
        userInfo.setClassTitle("1a");

        userInfo.setParent(userParent);

        userParent.setChild(new HashSet<>(Arrays.asList(userInfo)));

        return userInfoRepository.save(userParent);
    }
}
