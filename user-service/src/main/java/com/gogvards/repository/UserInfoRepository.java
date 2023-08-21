package com.gogvards.repository;

import com.gogvards.model.UserInfo;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;

import java.util.UUID;

@Repository
public interface UserInfoRepository extends ReactorCrudRepository<UserInfo, UUID> {
}
