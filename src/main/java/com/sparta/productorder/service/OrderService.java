package com.sparta.productorder.service;

import com.sparta.productorder.dto.request.OrderRequestDto;
import com.sparta.productorder.dto.response.OrderResponseDto;
import com.sparta.productorder.entity.Order;
import com.sparta.productorder.entity.Product;
import com.sparta.productorder.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public OrderResponseDto createOrder(Long id, OrderRequestDto requestDto) {
        Product product = productService.findProduct(id);
        product.decreaseStock(requestDto);

        Order order = new Order(requestDto, product);
        orderRepository.save(order);

        OrderResponseDto orderResponseDto = new OrderResponseDto(order);
        return orderResponseDto;
    }

    public List<OrderResponseDto> getOrders() {
        return orderRepository.findAll().stream().map(OrderResponseDto::new).toList();
    }

    public Page<OrderResponseDto> getOrdersList(Pageable pageable) {
        return orderRepository.findAll(pageable).map(OrderResponseDto::new);
    }
}
