package com.marjoz.modulith.customer;

import com.marjoz.modulith.customer.dto.CustomerDto;

class CustomerDataProvider {

    CustomerDto customerDto() {
        return CustomerDto.builder()
                          .withId(1L)
                          .withName("John")
                          .withSurname("Doe")
                          .withEmail("customer@email.pl")
                          .withAddress("23 Main Street New York")
                          .withLoyaltyPoints(20L)
                          .build();
    }
}