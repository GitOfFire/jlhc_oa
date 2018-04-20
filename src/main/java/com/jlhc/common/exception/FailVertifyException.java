package com.jlhc.common.exception;

import org.apache.shiro.authc.AuthenticationException;

public class FailVertifyException extends AuthenticationException {
    public FailVertifyException() {
    }

    public FailVertifyException(String message) {
        super(message);
    }

    public FailVertifyException(Throwable cause) {
        super(cause);
    }

    public FailVertifyException(String message, Throwable cause) {
        super(message, cause);
    }
}
