package com.marjoz.modulith.inventory.dto;

public record InventoryDto(Long id,
                           Long productId,
                           Integer quantity) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private Long productId;
        private Integer quantity;

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

        public InventoryDto build() {
            return new InventoryDto(id, productId, quantity);
        }
    }
}