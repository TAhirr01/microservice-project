package com.example.product_service.mapper;

import com.example.product_service.dto.ProductResponse;
import com.example.product_service.model.Product;

public class ProductMapper {
    public static ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }
}
