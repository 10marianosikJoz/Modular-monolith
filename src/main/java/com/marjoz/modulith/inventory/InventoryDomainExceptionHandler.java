package com.marjoz.modulith.inventory;

import com.marjoz.modulith.inventory.exception.InventoryDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class InventoryDomainExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleInventoryDomainException(InventoryDomainException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                                     exception.getMessage(),
                                                     System.currentTimeMillis()));
    }

    record ErrorResponse(int status, String message, long timestamp) {}
}