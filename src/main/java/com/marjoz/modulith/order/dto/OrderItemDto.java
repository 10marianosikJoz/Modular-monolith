package com.marjoz.modulith.order.dto;

import java.math.BigDecimal;

public record OrderItemDto(Long id,
                           Long productId,
                           Integer quantity,
                           BigDecimal price) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private Long productId;
        private Integer quantity;
        private BigDecimal price;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withProductId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public OrderItemDto build() {
            return new OrderItemDto(id, productId, quantity, price);
        }
    }
}