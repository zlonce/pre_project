package com.sparta.productorder.service;

import com.sparta.productorder.dto.request.OrderRequestDto;
import com.sparta.productorder.dto.response.OrderResponseDto;
import com.sparta.productorder.entity.Order;
import com.sparta.productorder.entity.Product;
import com.sparta.productorder.repository.OrderRepository;
import com.sparta.productorder.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderResponseDto createOrder(Long id, OrderRequestDto requestDto) {
        Product product = findProduct(id);
        product.decreaseStock(requestDto.getQuantity());

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

    public Product findProduct(Long id){
        return productRepository.findByIdForUpdate(id).orElseThrow(()->new IllegalArgumentException("상품이 존재하지 않습니다."));
    }
}
