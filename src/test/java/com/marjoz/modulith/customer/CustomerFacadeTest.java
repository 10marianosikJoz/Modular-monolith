package com.marjoz.modulith.customer;

import com.marjoz.modulith.customer.exception.CustomerDomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CustomerFacadeTest {

    private final CustomerModuleTestConfiguration customerModuleTestConfiguration = new CustomerModuleTestConfiguration();
    private final CustomerRepository customerRepository = customerModuleTestConfiguration.customerRepository();
    private final CustomerFacade customerFacade = customerModuleTestConfiguration.customerFacade();
    private final CustomerDataProvider customerDataProvider = new CustomerDataProvider();

    @BeforeEach
    void setUp() {
        customerRepository.truncate();
    }

    @Test
    void shouldFindAllCustomers() {
        //given
        var actualCustomer = customerDataProvider.customerDto();
        customerFacade.save(actualCustomer);

        //when
        var expectedCustomers = customerFacade.findAll();

        //then
        assertThat(expectedCustomers).isNotEmpty();
    }

    @Test
    void shouldFindCustomerById() {
        //given
        var actualCustomer = customerDataProvider.customerDto();
        var savedCustomer = customerFacade.save(actualCustomer);

        //when
        var expectedCustomer = customerFacade.findById(savedCustomer.id());

        //then
        assertThat(expectedCustomer).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        //given
        var customerId = 222L;

        //when
        //then
        assertThatThrownBy(() -> customerFacade.findById(customerId)).isInstanceOf(CustomerDomainException.class);
    }

    @Test
    void shouldDeleteCustomerById() {
        //given
        var actualCustomer = customerDataProvider.customerDto();
        var savedCustomer = customerFacade.save(actualCustomer);
        var savedCustomerId = savedCustomer.id();

        //when
        customerFacade.deleteById(savedCustomerId);

        //then
        assertThatThrownBy(() -> customerFacade.findById(savedCustomerId)).isInstanceOf(CustomerDomainException.class);
    }

    @Test
    void shouldSaveCustomer() {
        //given
        var actualCustomer = customerDataProvider.customerDto();

        //when
        customerFacade.save(actualCustomer);

        //then
        assertThat(customerRepository.findById(actualCustomer.id())).isNotNull();
    }

    @Test
    void shouldAddLoyaltyPoints() {
        //given
        var actualCustomer = customerDataProvider.customerDto();
        var savedCustomer = customerFacade.save(actualCustomer);
        var expectedLoyaltyPoints = 30L;

        //when
        customerFacade.addLoyaltyPoints(savedCustomer.id());
        var expectedCustomer = customerRepository.findById(savedCustomer.id());

        //then
        assertThat(expectedCustomer.loyaltyPoints()).isEqualTo(expectedLoyaltyPoints);
    }
}