package com.marjoz.modulith.product;

import com.marjoz.modulith.product.exception.ProductDomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductFacadeTest {

    private final ProductRepository productRepository = new ProductRepository();
    private final ProductFacade productFacade = new ProductFacade(productRepository, new ProductMapper());
    private final ProductDataProvider productDataProvider = new ProductDataProvider();

    @BeforeEach
    void setUp() {
        productRepository.truncate();
    }

    @Test
    void shouldFindAllProducts() {
        //given
        var actualProduct = productDataProvider.productDto();
        productFacade.save(actualProduct);

        //when
        var expectedProducts = productFacade.findAll();

        //then
        assertThat(expectedProducts).isNotEmpty();
    }

    @Test
    void shouldFindProductById() {
        //given
        var actualProduct = productDataProvider.productDto();
        var savedProduct = productFacade.save(actualProduct);

        //when
        var expectedProduct = productFacade.findProductById(savedProduct.id());

        //then
        assertThat(expectedProduct).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        //given
        var productId = 222L;

        //when
        //then
        assertThatThrownBy(() -> productFacade.findProductById(productId)).isInstanceOf(ProductDomainException.class);
    }

    @Test
    void shouldDeleteProductById() {
        //given
        var actualProduct = productDataProvider.productDto();
        var savedProduct = productFacade.save(actualProduct);

        //when
        productFacade.deleteProductById(savedProduct.id());

        //then
        assertThat(productFacade.findAll()).isEmpty();
    }

    @Test
    void shouldSaveProduct() {
        //given
        var actualProduct = productDataProvider.productDto();

        //when
        var savedProduct = productFacade.save(actualProduct);

        //then
        assertThat(savedProduct).isNotNull();
    }
}