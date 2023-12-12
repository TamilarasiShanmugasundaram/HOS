package com.Hos.core.common.exception;

public class CustomException extends RuntimeException{
    private String message;
    private String errorCode;

    public CustomException(String message, String errorCode) {
        super(message);
        errorCode = this.errorCode;
    }
}
