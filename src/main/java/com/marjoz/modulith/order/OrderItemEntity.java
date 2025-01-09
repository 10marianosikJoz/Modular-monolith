package com.marjoz.modulith.order;

import java.math.BigDecimal;

record OrderItemEntity(Long id,
                       Long productId,
                       Integer quantity,
                       BigDecimal price) {

    static Builder builder() {
        return new Builder();
    }

    static class Builder {

        private Long id;
        private Long productId;
        private Integer quantity;
        private BigDecimal price;

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

        Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        OrderItemEntity build() {
            return new OrderItemEntity(id, productId, quantity, price);
        }
    }
}