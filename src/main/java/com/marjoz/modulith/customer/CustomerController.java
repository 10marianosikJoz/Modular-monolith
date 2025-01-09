package com.marjoz.modulith.customer;

import com.marjoz.modulith.customer.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
class CustomerController {

    private final CustomerFacade customerFacade;

    CustomerController(final CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GetMapping("/{customerId}")
    ResponseEntity<CustomerDto> findCustomerById(@PathVariable Long customerId) {
        var customer = customerFacade.findById(customerId);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        customerFacade.save(customerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{customerId}")
    ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerFacade.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }
}