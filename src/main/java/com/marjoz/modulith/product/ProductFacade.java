package com.marjoz.modulith.product;

import com.marjoz.modulith.product.dto.ProductDto;

import java.util.List;

public class ProductFacade {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    ProductFacade(final ProductRepository productRepository,
                  final ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                                          .map(productMapper::toDto)
                                          .toList();
    }

    public ProductDto findProductById(Long id) {
        var product = productRepository.findById(id);
        return productMapper.toDto(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDto save(ProductDto productDto) {
        var savedProduct = productRepository.save(productMapper.toEntity(productDto));
        return productMapper.toDto(savedProduct);
    }
}