package com.sak.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long productId;             // ID of the product
    private String productName;         // Name of the product
    private String productDescription;  // Description of the product
    private Double price;               // Price of the product
    private Long quantity;              // Available quantity
    private String base64Image;         // Base64 encoded image string
}
