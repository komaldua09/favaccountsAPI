package com.learning.bank.exception;

import com.learning.bank.model.APIError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<APIError> handleException(BusinessException ex) {
        APIError apiError = new APIError(ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.status(ex.getHttpStatus()).body(apiError);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handleException(MethodArgumentNotValidException ex) {
        APIError apiError = new APIError(ex.getMessage(), "400");
        return ResponseEntity.badRequest().body(apiError);
    }
}
