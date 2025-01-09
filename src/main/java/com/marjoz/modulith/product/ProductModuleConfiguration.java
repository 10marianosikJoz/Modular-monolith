package com.marjoz.modulith.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class ProductModuleConfiguration {

    @Bean
    ProductFacade productFacade(ProductRepository productRepository) {
        return new ProductFacade(productRepository,
                                 new ProductMapper());
    }

    @Bean
    ProductRepository productRepository() {
        return new ProductRepository();
    }
}