package com.itvdn.exceptions;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(String msg) {
        super(msg);
    }
}
