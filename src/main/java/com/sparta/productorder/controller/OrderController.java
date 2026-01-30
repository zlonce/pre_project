package com.sparta.productorder.controller;

import com.sparta.productorder.dto.request.OrderRequestDto;
import com.sparta.productorder.dto.response.OrderResponseDto;
import com.sparta.productorder.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/{id}")
    public OrderResponseDto createOrder(@PathVariable Long id) {
        return orderService.createOrder(id);
    }

    @GetMapping()
    public List<OrderResponseDto> getOrders(){
        return orderService.getOrders();
    }
}
