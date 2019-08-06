package com.coffeebeans.demoapp.common.data.net.error;

public class RestApiErrorException extends RuntimeException {

    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int BAD_REQUEST = 400;
    public static final int USER_LOCKED = 423;

    private int statusCode;

    public RestApiErrorException(String detailMessage, int statusCode) {
        super(detailMessage);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
