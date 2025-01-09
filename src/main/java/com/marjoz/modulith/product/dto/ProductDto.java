package com.marjoz.modulith.product.dto;

import java.time.LocalDate;

public record ProductDto(Long id,
                         String name,
                         LocalDate expirationDate) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String name;
        private LocalDate expirationDate;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withExpirationDate(LocalDate expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public ProductDto build() {
            return new ProductDto(id, name, expirationDate);
        }
    }
}