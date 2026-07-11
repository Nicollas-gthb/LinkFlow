package com.linkflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex){
        Map<String, String> fields = new HashMap<>();

        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            fields.put(error.getField(), error.getDefaultMessage());
        }

        ErrorResponse response = new ErrorResponse("Erro de validação", HttpStatus.UNPROCESSABLE_ENTITY.value(), fields);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }


    public record ErrorResponse(
            String message,
            int status,
            OffsetDateTime timestamp,
            Map<String, String> fields
    ) {
        public ErrorResponse(String message, int status) {
            this(message, status, OffsetDateTime.now(), null);
        }

        public ErrorResponse(String message, int status, Map<String, String> fields) {
            this(message, status, OffsetDateTime.now(), fields);
        }
    }
}
