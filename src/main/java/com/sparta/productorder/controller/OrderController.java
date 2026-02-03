package com.sparta.productorder.controller;

import com.sparta.productorder.dto.request.OrderRequestDto;
import com.sparta.productorder.dto.response.OrderResponseDto;
import com.sparta.productorder.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/{id}")
    public OrderResponseDto createOrder(@PathVariable Long id, @RequestBody OrderRequestDto requestDto) {
        return orderService.createOrder(id, requestDto);
    }

    @GetMapping()
    public List<OrderResponseDto> getOrders(){
        return orderService.getOrders();
    }

    @GetMapping("/list")
    public Page<OrderResponseDto> getOrdersList(@PageableDefault(page=0, size=5, sort="orderId") Pageable pageable){
        return orderService.getOrdersList(pageable);
    }
}
