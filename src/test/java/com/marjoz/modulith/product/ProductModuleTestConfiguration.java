package com.marjoz.modulith.product;

public class ProductModuleTestConfiguration {

    public ProductFacade productFacade() {
        return new ProductFacade(productRepository(), new ProductMapper());
    }

    private ProductRepository productRepository() {
        return new ProductRepository();
    }
}