package com.example.ECommerce.ResponseDTOs;

import com.example.ECommerce.Enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {

    private String name;

    private int price;

    private int quantity;

    private ProductStatus productStatus;
}
