package com.marjoz.modulith.order;

import com.marjoz.modulith.customer.CustomerFacade;
import com.marjoz.modulith.order.dto.OrderDto;
import com.marjoz.modulith.order.dto.OrderItemDto;
import com.marjoz.modulith.product.ProductFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderFacade.class);

    private final OrderRepository orderRepository;
    private final ProductFacade productFacade;
    private final CustomerFacade customerFacade;
    private final OrderMapper orderMapper;

    OrderFacade(final OrderRepository orderRepository,
                final ProductFacade productFacade,
                final CustomerFacade customerFacade,
                final OrderMapper orderMapper) {

        this.orderRepository = orderRepository;
        this.productFacade = productFacade;
        this.customerFacade = customerFacade;
        this.orderMapper = orderMapper;
    }

    public String verifyProductExpirationDate(Long productId) {
        var product = productFacade.findProductById(productId);

        if (product.expirationDate().isAfter(LocalDate.now())) {
            return "Product is not expired. Expiration date: " + product.expirationDate();
        } else {
            return "Product is expired. Expiration date: " + product.expirationDate();
        }
    }

    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream()
                                        .map(orderMapper::toDto)
                                        .collect(Collectors.toList());
    }

    public OrderDto findOrderById(Long id) {
        var order = orderRepository.findById(id);
        return orderMapper.toDto(order);
    }

    public OrderDto createOrder(OrderDto orderDto) {
        var order = orderRepository.save(orderMapper.toEntity(orderDto));
        customerFacade.addLoyaltyPoints(order.customerId());

        return orderMapper.toDto(order);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDto addOrderItem(Long orderId, OrderItemDto orderItemDto) {
        var order = orderRepository.findById(orderId);
        order = order.addOrderItem(orderMapper.toOrderItem(orderItemDto));
        orderRepository.save(order);

        LOGGER.info("Added order item: {}", order.orderItems());

        return orderMapper.toDto(order);
    }
}