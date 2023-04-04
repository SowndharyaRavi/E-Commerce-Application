package com.example.ECommerce.Controllers;

import com.example.ECommerce.RequestDTOs.SellerRequestDTO;
import com.example.ECommerce.Services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/addSeller")
    public String addSeller(@RequestBody SellerRequestDTO sellerRequestDTO){
        return sellerService.addSeller(sellerRequestDTO);
    }

    //add all sellers

    //get a seller by pancard

    //find sellers of particular age

    //http://localhost:8080/swagger-ui.html-swagger
}
