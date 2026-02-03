package com.sparta.productorder.dto.request;

import lombok.Getter;

@Getter
public class OrderRequestDto {
    private Long productId;
    private int quantity;
}
