package com.marjoz.modulith.inventory;

import com.marjoz.modulith.inventory.exception.InventoryDomainException;

record InventoryEntity(Long id,
                       Long productId,
                       Integer quantity) {

    InventoryEntity addToStock(int amount) {
        return new InventoryEntity(id, productId, quantity + amount);
    }

    InventoryEntity removeFromStock(int amount) {
        if (amount > quantity) {
            throw new InventoryDomainException("Not enough stock.");
        }
        return new InventoryEntity(id, productId, quantity - amount);
    }

    static Builder builder() {
        return new Builder();
    }

    static class Builder {

        private Long id;
        private Long productId;
        private Integer quantity;

        Builder withId(Long id) {
            this.id = id;
            return this;
        }

        Builder withProductId(Long productId) {
            this.productId = productId;
            return this;
        }

        Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        InventoryEntity build() {
            return new InventoryEntity(id, productId, quantity);
        }
    }
}