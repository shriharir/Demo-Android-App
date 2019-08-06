package com.coffeebeans.demoapp.common.presentation.dependency.module;

import android.content.Context;

import com.coffeebeans.demoapp.common.data.net.RestApi;
import com.coffeebeans.demoapp.common.data.net.interceptor.HttpInterceptor;
import com.coffeebeans.demoapp.login.data.repository.UserDataRepository;
import com.coffeebeans.demoapp.login.domain.repository.UserRepository;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    @Singleton
    RestApi provideRestApi(Context context) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpInterceptor(context))
                .build();

        GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create());

        return new Retrofit.Builder()
                .baseUrl(RestApi.URL_BASE)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(RestApi.class);
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(RestApi restApi) {
        return new UserDataRepository(restApi);
    }
}
