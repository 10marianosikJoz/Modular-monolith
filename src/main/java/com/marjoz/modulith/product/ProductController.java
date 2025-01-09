package com.marjoz.modulith.product;

import com.marjoz.modulith.product.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
class ProductController {

    private final ProductFacade productFacade;

    ProductController(final ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping
    ResponseEntity<List<ProductDto>> findAllProducts() {
        var products = productFacade.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    ResponseEntity<ProductDto> findProductById(@PathVariable Long productId) {
        var product = productFacade.findProductById(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        var savedProduct = productFacade.save(productDto);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/{productId}")
    ResponseEntity<Void> deleteProductById(@PathVariable Long productId) {
        productFacade.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }
}