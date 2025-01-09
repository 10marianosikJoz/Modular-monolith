package com.marjoz.modulith.product;

import java.time.LocalDate;

record ProductEntity(Long id, String name, LocalDate expirationDate) {

    static Builder builder() {
        return new Builder();
    }

    static class Builder {

        private Long id;
        private String name;
        private LocalDate expirationDate;

        Builder withId(Long id) {
            this.id = id;
            return this;
        }

        Builder withName(String name) {
            this.name = name;
            return this;
        }

        Builder withExpirationDate(LocalDate expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        ProductEntity build() {
            return new ProductEntity(id, name, expirationDate);
        }
    }
}