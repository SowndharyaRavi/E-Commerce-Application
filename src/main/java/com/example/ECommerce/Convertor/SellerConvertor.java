package com.example.ECommerce.Convertor;

import com.example.ECommerce.Models.Seller;
import com.example.ECommerce.RequestDTOs.SellerRequestDTO;
import lombok.experimental.UtilityClass;

import java.nio.Buffer;

@UtilityClass
public class SellerConvertor {

    public static Seller convertDTOtoSeller(SellerRequestDTO sellerRequestDTO){
        Seller seller= Seller.builder().name(sellerRequestDTO.getName())
                .mobNo(sellerRequestDTO.getMobNo()).email(sellerRequestDTO.getEmail())
                .panNo(sellerRequestDTO.getPanNo()).build();

        return seller;
    }
}
