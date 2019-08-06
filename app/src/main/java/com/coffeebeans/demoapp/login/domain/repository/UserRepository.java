package com.coffeebeans.demoapp.login.domain.repository;

import com.coffeebeans.demoapp.login.domain.live.entity.AuthResponse;
import com.coffeebeans.demoapp.login.domain.live.entity.UserEntity;

import io.reactivex.Observable;

public interface UserRepository {
    Observable<AuthResponse> loginUser(UserEntity user);
}
