package com.example.ECommerce.RequestDTOs;

import com.example.ECommerce.Enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private int sellerId;

    private String name;

    private int price;

    private int quantity;

    private Category category;
}
