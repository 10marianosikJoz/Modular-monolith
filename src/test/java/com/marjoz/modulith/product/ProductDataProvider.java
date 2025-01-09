package com.marjoz.modulith.product;

import com.marjoz.modulith.product.dto.ProductDto;

import java.time.LocalDate;

class ProductDataProvider {

    ProductDto productDto() {
        return ProductDto.builder()
                         .withId(1L)
                         .withName("Corn flakes")
                         .withExpirationDate(LocalDate.now())
                         .build();
    }
}