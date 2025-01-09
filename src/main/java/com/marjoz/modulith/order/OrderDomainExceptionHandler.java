package com.marjoz.modulith.order;

import com.marjoz.modulith.order.exception.OrderDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class OrderDomainExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleOrderDomainException(OrderDomainException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                                     exception.getMessage(),
                                                     System.currentTimeMillis()));
    }

    record ErrorResponse(int status, String message, long timestamp) {}
}