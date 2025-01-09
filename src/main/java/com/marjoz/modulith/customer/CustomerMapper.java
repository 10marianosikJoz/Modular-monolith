package com.marjoz.modulith.customer;

import com.marjoz.modulith.customer.dto.CustomerDto;

class CustomerMapper {

    CustomerDto toDto(CustomerEntity customerEntity) {
        return CustomerDto.builder()
                           .withId(customerEntity.id())
                           .withName(customerEntity.name())
                           .withSurname(customerEntity.surname())
                           .withEmail(customerEntity.email())
                           .withAddress(customerEntity.address())
                           .withLoyaltyPoints(customerEntity.loyaltyPoints())
                           .build();
    }

    CustomerEntity toEntity(CustomerDto customerDto) {
        return CustomerEntity.builder()
                             .withId(customerDto.id())
                             .withName(customerDto.name())
                             .withSurname(customerDto.surname())
                             .withEmail(customerDto.email())
                             .withAddress(customerDto.address())
                             .withLoyaltyPoints(customerDto.loyaltyPoints())
                             .build();
    }
}