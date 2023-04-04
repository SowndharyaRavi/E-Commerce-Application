package com.example.ECommerce.ResponseDTOs;

import com.example.ECommerce.Enums.Category;
import com.example.ECommerce.Enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDTO {

    private String productName;

    private int price;

    private Category category;

    private ProductStatus productStatus;
}
