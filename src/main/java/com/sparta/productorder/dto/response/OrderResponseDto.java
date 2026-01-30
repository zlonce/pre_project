package com.sparta.productorder.dto.response;

import com.sparta.productorder.entity.Order;
import com.sparta.productorder.entity.Product;
import lombok.Getter;

@Getter
public class OrderResponseDto {
    private Long orderId;
    private Long productId;
    private String productName;
    private int stock;

    public OrderResponseDto(Order order) {
        this.orderId = order.getOrderId();
        this.productId = order.getProduct().getId();
        this.productName = order.getProduct().getProductName();
        this.stock = order.getProduct().getStock();
    }
}
