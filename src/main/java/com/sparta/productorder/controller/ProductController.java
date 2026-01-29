package com.sparta.productorder.controller;

import com.sparta.productorder.dto.request.ProductRequestDto;
import com.sparta.productorder.dto.response.ProductResponseDto;
import com.sparta.productorder.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto RequestDto) {
        return productService.createProduct(RequestDto);
    }

    @GetMapping()
    public List<ProductResponseDto> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public List<ProductResponseDto> getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public int updateProduct(@PathVariable int id, @RequestBody ProductRequestDto requestDto) {
        return productService.updateProduct(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public int deleteProduct(@PathVariable int id) {
        return productService.deleteProducts(id);
    }

}
