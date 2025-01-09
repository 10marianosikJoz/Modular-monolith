package com.marjoz.modulith.product;

import com.marjoz.modulith.product.dto.ProductDto;

class ProductMapper {

    ProductDto toDto(ProductEntity productEntity) {
        return ProductDto.builder()
                         .withId(productEntity.id())
                         .withName(productEntity.name())
                         .withExpirationDate(productEntity.expirationDate())
                         .build();
    }

    ProductEntity toEntity(ProductDto productDto) {
        return ProductEntity.builder()
                             .withId(productDto.id())
                             .withName(productDto.name())
                             .withExpirationDate(productDto.expirationDate())
                             .build();
    }
}