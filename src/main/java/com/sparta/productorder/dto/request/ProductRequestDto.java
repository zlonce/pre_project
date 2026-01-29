package com.sparta.productorder.dto.request;

import lombok.Getter;

@Getter
public class ProductRequestDto {
    private String productName;
    private int price;
    private int stock;
}
