package com.example.product_service.mapper;

import com.example.product_service.dto.ProductResponse;
import com.example.product_service.model.Product;

import java.util.List;

public class ProductMapper {
    public static ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }


    public static List<ProductResponse> getAllProducts(List<Product> products) {
        return products.stream().map(ProductMapper::mapToProductResponse).toList();
    }
}
