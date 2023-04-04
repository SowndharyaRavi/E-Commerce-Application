package com.example.ECommerce.Services;

import com.example.ECommerce.Convertor.SellerConvertor;
import com.example.ECommerce.Models.Seller;
import com.example.ECommerce.Repository.SellerRepository;
import com.example.ECommerce.RequestDTOs.SellerRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public String addSeller(SellerRequestDTO sellerRequestDTO){
        Seller seller= SellerConvertor.convertDTOtoSeller(sellerRequestDTO);
        sellerRepository.save(seller);
        return "Seller has been added";
    }
}
