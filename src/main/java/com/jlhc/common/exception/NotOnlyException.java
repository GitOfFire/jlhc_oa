package com.jlhc.common.exception;

public class NotOnlyException extends RuntimeException{
    public NotOnlyException() {
    }

    public NotOnlyException(String message) {
        super(message);
    }

    public NotOnlyException(Throwable cause) {
        super(cause);
    }

    public NotOnlyException(String message, Throwable cause) {
        super(message, cause);
    }
}
