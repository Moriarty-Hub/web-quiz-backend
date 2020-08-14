package com.thoughtworks.quiz.exception;

public class NameAlreadyExistException extends RuntimeException {
    private final String errorMessage;

    public NameAlreadyExistException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
