package com.marjoz.modulith.product;

import com.marjoz.modulith.product.exception.ProductDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ProductDomainExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleProductDomainException(ProductDomainException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                        exception.getMessage(),
                        System.currentTimeMillis()));
    }

    record ErrorResponse(int status, String message, long timestamp) {}
}