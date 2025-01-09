package com.marjoz.modulith.customer;

record CustomerEntity(Long id,
                      String name,
                      String surname,
                      String email,
                      String address,
                      Long loyaltyPoints) {

    CustomerEntity addLoyaltyPoints(Long points) {
        var newLoyaltyPoints = this.loyaltyPoints + points;
        return new CustomerEntity(this.id, this.name, this.surname, this.email, this.address, newLoyaltyPoints);
    }

    static Builder builder() {
        return new Builder();
    }

    static class Builder {

        private Long id;
        private String name;
        private String surname;
        private String email;
        private String address;
        private Long loyaltyPoints;

        Builder withId(Long id) {
            this.id = id;
            return this;
        }

        Builder withName(String name) {
            this.name = name;
            return this;
        }

        Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        Builder withLoyaltyPoints(Long loyaltyPoints) {
            this.loyaltyPoints = loyaltyPoints;
            return this;
        }

        CustomerEntity build() {
            return new CustomerEntity(id, name, surname, email, address, loyaltyPoints);
        }
    }
}