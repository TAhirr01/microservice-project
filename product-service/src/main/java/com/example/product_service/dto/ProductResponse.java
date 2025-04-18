package com.example.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigInteger price;
}
