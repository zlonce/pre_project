package com.sparta.productorder.service;

import com.sparta.productorder.dto.request.ProductRequestDto;
import com.sparta.productorder.dto.response.ProductResponseDto;
import com.sparta.productorder.entity.Product;
import com.sparta.productorder.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(ProductRequestDto requestDto){
        Product product = new Product(requestDto);
        productRepository.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto(product);
        return productResponseDto;

    }

    public List<ProductResponseDto> getProducts() {
        return productRepository.findAllByOrderById().stream().map(ProductResponseDto::new).toList();
    }

    @Transactional
    public int updateProduct(int id, ProductRequestDto requestDto) {
        Product product = findProduct(id);

        product.update(requestDto);
        return id;
    }

    public int deleteProducts(int id) {
        Product product = findProduct(id);
        productRepository.delete(product);
        return id;
    }

    public Product findProduct(int id){
        return productRepository.findById(id).orElse(null);
    }
}
