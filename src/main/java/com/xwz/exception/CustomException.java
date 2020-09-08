package com.xwz.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
