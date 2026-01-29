package com.sparta.productorder.dto.response;

import com.sparta.productorder.entity.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private Long id;
    private String productName;
    private int price;
    private int stock;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }
}
