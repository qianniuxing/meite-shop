package com.hello.exception;

public class ResponseCodeException extends Exception {

    public ResponseCodeException() {
        super();
    }

    public ResponseCodeException(String message) {
        super(message);
    }

    public ResponseCodeException(String message, Throwable cause) {
        super(message, cause);
    }

}
