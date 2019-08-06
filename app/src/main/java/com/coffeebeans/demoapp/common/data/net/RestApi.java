package com.coffeebeans.demoapp.common.data.net;

import com.coffeebeans.demoapp.BuildConfig;
import com.coffeebeans.demoapp.login.domain.live.entity.AuthResponse;
import com.coffeebeans.demoapp.login.domain.live.entity.UserEntity;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RestApi {

    String URL_BASE = BuildConfig.BASE_URL;

    @Headers("Accept: application/json")
    @POST("/user/auth")
    Observable<Response<AuthResponse>> doLogin(@Body UserEntity userEntity);

}
