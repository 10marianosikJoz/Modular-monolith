package com.marjoz.modulith.order;

import com.marjoz.modulith.customer.CustomerFacade;
import com.marjoz.modulith.customer.CustomerModuleTestConfiguration;
import com.marjoz.modulith.customer.CustomerRepositoryTestApi;
import com.marjoz.modulith.order.dto.OrderItemDto;
import com.marjoz.modulith.order.exception.OrderDomainException;
import com.marjoz.modulith.product.ProductFacade;
import com.marjoz.modulith.product.ProductModuleTestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderFacadeTest {

    private final OrderModuleTestConfiguration orderModuleTestConfiguration = new OrderModuleTestConfiguration();
    private final ProductModuleTestConfiguration productModuleTestConfiguration = new ProductModuleTestConfiguration();
    private final CustomerModuleTestConfiguration customerModuleTestConfiguration = new CustomerModuleTestConfiguration();

    private final ProductFacade productFacade = productModuleTestConfiguration.productFacade();
    private final CustomerFacade customerFacade = customerModuleTestConfiguration.customerFacade();
    private final OrderFacade orderFacade = orderModuleTestConfiguration.orderFacade();

    private final OrderRepository orderRepository = orderModuleTestConfiguration.orderRepository();

    private final OrderDataProvider orderDataProvider = new OrderDataProvider();
    private final CustomerRepositoryTestApi customerRepositoryTestApi = new CustomerRepositoryTestApi();

    @BeforeEach
    void setUp() {
        orderRepository.truncate();
        customerRepositoryTestApi.truncate();
    }

    @Test
    void shouldFindAllOrders() {
        // given
        var actualCustomer = orderDataProvider.customerDto();
        var actualOrder = orderDataProvider.orderDto();

        customerFacade.save(actualCustomer);
        orderFacade.createOrder(actualOrder);

        //when
        var expectedOrders = orderFacade.findAll();

        //then
        assertThat(expectedOrders).isNotEmpty();
    }

    @Test
    void shouldFindOrderById() {
        // given
        var actualCustomer = orderDataProvider.customerDto();
        var actualOrder = orderDataProvider.orderDto();
        customerFacade.save(actualCustomer);
        var savedOrder = orderFacade.createOrder(actualOrder);

        //when
        var expectedOrder = orderFacade.findOrderById(savedOrder.id());

        //then
        assertThat(expectedOrder).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenOrderDoesNotExist() {
        // given
        var orderId = 222L;

        //when
        //then
        assertThatThrownBy(() -> orderFacade.findOrderById(orderId)).isInstanceOf(OrderDomainException.class);
    }

    @Test
    void shouldDeleteOrderById() {
        // given
        var actualCustomer = orderDataProvider.customerDto();
        var actualOrder = orderDataProvider.orderDto();
        customerFacade.save(actualCustomer);
        var savedOrder = orderFacade.createOrder(actualOrder);
        var savedProductId = savedOrder.id();

        //when
        orderFacade.deleteOrderById(savedProductId);

        //then
        assertThatThrownBy(() -> orderFacade.findOrderById(savedProductId)).isInstanceOf(OrderDomainException.class);
    }

    @Test
    void shouldCreateOrder() {
        // given
        var actualCustomer = orderDataProvider.customerDto();
        var actualOrder = orderDataProvider.orderDto();
        customerFacade.save(actualCustomer);

        //when
        var savedOrder = orderFacade.createOrder(actualOrder);

        //then
        assertThat(orderRepository.findById(savedOrder.id())).isNotNull();
    }

    @Test
    void shouldAddOrderItem() {
        // given
        var actualCustomer = orderDataProvider.customerDto();
        var actualOrder = orderDataProvider.orderDto();
        customerFacade.save(actualCustomer);
        var savedOrder = orderFacade.createOrder(actualOrder);
        var actualOrderItem = orderDataProvider.orderItemDto();

        //when
        var expectedOrder = orderFacade.addOrderItem(savedOrder.id(), actualOrderItem);

        //then
        assertThat(expectedOrder.orderItems()).hasSize(2);
    }

    @Test
    void shouldVerifyProductExpirationDate() {
        // given
        var actualProduct = orderDataProvider.productDto();
        productFacade.save(actualProduct);

        var actualCustomer = orderDataProvider.customerDto();
        customerFacade.save(actualCustomer);

        var actualOrder = orderDataProvider.orderDto();
        var savedOrder = orderFacade.createOrder(actualOrder);

        var actualOrderItems = orderFacade.addOrderItem(savedOrder.id(), orderDataProvider.orderItemDto());
        var actualProductId = actualOrderItems.orderItems().stream().map(OrderItemDto::productId).findFirst().get();

        //when
        var expectedResult = orderFacade.verifyProductExpirationDate(actualProductId);

        //then
        assertThat(expectedResult).isEqualTo("Product is not expired. Expiration date: " + actualProduct.expirationDate());
    }
}