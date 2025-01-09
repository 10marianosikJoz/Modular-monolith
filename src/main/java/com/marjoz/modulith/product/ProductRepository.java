package com.marjoz.modulith.product;

import com.marjoz.modulith.product.exception.ProductDomainException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class ProductRepository {

    private static final Map<Long, ProductEntity> DATABASE = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    void truncate() {
        DATABASE.clear();
    }

    ProductEntity save(ProductEntity productEntity) {
        if (productEntity.id() == null) {
            productEntity = new ProductEntity(ID_GENERATOR.getAndIncrement(), productEntity.name(), productEntity.expirationDate());
        }
        DATABASE.put(productEntity.id(), productEntity);
        return productEntity;
    }

    List<ProductEntity> findAll() {
        return DATABASE.values().stream().toList();
    }

    ProductEntity findById(Long id) {
        var entity = DATABASE.get(id);
        if (entity == null) {
            throw new ProductDomainException("Product with id " + id + " not found");
        }
        return entity;
    }

    void deleteById(Long id) {
        DATABASE.remove(id);
    }
}