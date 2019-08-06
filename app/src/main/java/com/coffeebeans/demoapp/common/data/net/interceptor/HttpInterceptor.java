package com.coffeebeans.demoapp.common.data.net.interceptor;

import android.content.Context;

import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class HttpInterceptor implements Interceptor {

    private Context context;

    @Inject
    public HttpInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request initialRequest = chain.request().newBuilder()
                .addHeader("Accept-Language", Locale.getDefault().getLanguage())
                .build();

        Response response = chain.proceed(initialRequest);
        return response;
    }

}
