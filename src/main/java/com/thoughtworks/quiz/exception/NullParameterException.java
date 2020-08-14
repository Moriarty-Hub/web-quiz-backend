package com.thoughtworks.quiz.exception;

public class NullParameterException extends RuntimeException {

    private final String errorMessage;

    public NullParameterException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
