package com.coffeebeans.demoapp.common.data.repository;

import com.coffeebeans.demoapp.common.data.net.error.ResponseErrorEntity;
import com.coffeebeans.demoapp.common.data.net.error.RestApiErrorException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import retrofit2.Response;

public abstract class RestApiRepository {

    protected void handleResponseError(Response response) {
        if (!response.isSuccessful()) {
            ResponseErrorEntity errorWrapper;
            try {
                errorWrapper = new Gson().fromJson(response.errorBody() != null ? response.errorBody().string() : null, ResponseErrorEntity.class);
                throw new RestApiErrorException(errorWrapper.getMessage(), response.code());
            } catch (IOException | JsonSyntaxException | NullPointerException e) {
                throw new RestApiErrorException(response.message(), response.code());
            }
        }
    }
}
