package com.marjoz.modulith.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class CustomerModuleConfiguration {

    @Bean
    CustomerFacade customerFacade(CustomerRepository customerRepository) {
        return new CustomerFacade(customerRepository, new CustomerMapper());
    }

    @Bean
    CustomerRepository customerRepository() {
        return new CustomerRepository();
    }
}