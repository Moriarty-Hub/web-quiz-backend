package com.thoughtworks.quiz.component;

import com.thoughtworks.quiz.controller.ItemController;
import com.thoughtworks.quiz.exception.NullParameterException;
import com.thoughtworks.quiz.exception.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = ItemController.class)
public class ItemExceptionHandler {

    @ExceptionHandler(NullParameterException.class)
    public ResponseEntity<Error> nullParameterExceptionHandler(NullParameterException nullParameterException) {
        return ResponseEntity.badRequest().body(new Error(nullParameterException.getMessage()));
    }
}
