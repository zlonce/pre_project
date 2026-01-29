package com.sparta.productorder.entity;

import com.sparta.productorder.dto.request.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="product_name", nullable = false)
    private String productName;
    @Column(name="price", nullable = false)
    private int price;
    @Column(name="stock", nullable= false)
    private int stock;

    public Product(ProductRequestDto product) {
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }

    public void update(ProductRequestDto requestDto) {
        this.productName = requestDto.getProductName();
        this.price = requestDto.getPrice();
        this.stock = requestDto.getStock();
    }
}
