package com.unitech.exception;

import com.unitech.dto.TransferResponse;
import com.unitech.model.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> validate(NotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new GenericResponse<>(ex.getMessage(), null));
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<TransferResponse> validate(InvalidRequestException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(TransferResponse.builder()
                        .message(ex.getMessage())
                        .build());
    }
}