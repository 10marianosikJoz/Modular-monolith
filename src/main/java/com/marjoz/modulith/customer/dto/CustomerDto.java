package com.marjoz.modulith.customer.dto;

public record CustomerDto(Long id,
                          String name,
                          String surname,
                          String email,
                          String address,
                          Long loyaltyPoints) {


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String name;
        private String surname;
        private String email;
        private String address;
        private Long loyaltyPoints;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
    }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withLoyaltyPoints(Long loyaltyPoints) {
            this.loyaltyPoints = loyaltyPoints;
            return this;
        }

        public CustomerDto build() {
            return new CustomerDto(id, name, surname, email, address, loyaltyPoints);
        }
    }
}