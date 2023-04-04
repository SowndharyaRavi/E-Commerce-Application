package com.example.ECommerce.Convertor;

import com.example.ECommerce.Enums.ProductStatus;
import com.example.ECommerce.Models.Product;
import com.example.ECommerce.RequestDTOs.ProductRequestDTO;
import com.example.ECommerce.ResponseDTOs.ProductResponseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConvertor {

    public static Product convertDTOtoProduct(ProductRequestDTO productRequestDTO){

        Product product=Product.builder().name(productRequestDTO.getName()).price(productRequestDTO.getPrice())
                .quantity(productRequestDTO.getQuantity()).category(productRequestDTO.getCategory())
                .productStatus(ProductStatus.AVAILABLE).build();

        return product;
    }

    public static ProductResponseDTO convertProductToDTO(Product product){

        ProductResponseDTO productResponseDTO=ProductResponseDTO.builder()
                .name(product.getName()).productStatus(product.getProductStatus())
                .price(product.getPrice()).quantity(product.getQuantity()).build();

        return productResponseDTO;
    }
}
