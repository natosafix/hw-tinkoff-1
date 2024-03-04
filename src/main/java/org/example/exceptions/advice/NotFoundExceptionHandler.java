package org.example.exceptions.advice;

import lombok.AllArgsConstructor;
import org.example.exceptions.BaseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(BaseNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleMessageNotFoundException(BaseNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getReason()));
    }

    @AllArgsConstructor
    public static class ErrorResponse {
        public String message;
    }
}


