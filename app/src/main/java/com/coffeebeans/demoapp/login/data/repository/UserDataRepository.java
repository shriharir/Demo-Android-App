package com.coffeebeans.demoapp.login.data.repository;

import com.coffeebeans.demoapp.common.data.net.RestApi;
import com.coffeebeans.demoapp.common.data.repository.RestApiRepository;
import com.coffeebeans.demoapp.login.domain.live.entity.AuthResponse;
import com.coffeebeans.demoapp.login.domain.live.entity.UserEntity;
import com.coffeebeans.demoapp.login.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserDataRepository extends RestApiRepository implements UserRepository {

    private final RestApi restApi;

    @Inject
    public UserDataRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<AuthResponse> loginUser(UserEntity user) {
        return this.restApi.doLogin(user)
                .map(userEntityResponse -> {
                    handleResponseError(userEntityResponse);
                    AuthResponse response = new AuthResponse("Success");
                    return response;
                });
    }
}
