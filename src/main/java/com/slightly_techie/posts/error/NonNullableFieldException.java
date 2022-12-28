package com.slightly_techie.posts.error;

public class NonNullableFieldException extends Exception{
    public NonNullableFieldException() {
        super();
    }

    public NonNullableFieldException(String message) {
        super(message);
    }

    public NonNullableFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonNullableFieldException(Throwable cause) {
        super(cause);
    }

    protected NonNullableFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
