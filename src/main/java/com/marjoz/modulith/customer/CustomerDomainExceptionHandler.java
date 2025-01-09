package com.marjoz.modulith.customer;

import com.marjoz.modulith.customer.exception.CustomerDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class CustomerDomainExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleCustomerDomainException(CustomerDomainException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                                     exception.getMessage(),
                                                     System.currentTimeMillis()));
    }

    record ErrorResponse(int status, String message, long timestamp) {}
}