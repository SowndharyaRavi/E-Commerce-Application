package com.example.ECommerce.Controllers;

import com.example.ECommerce.Models.Product;
import com.example.ECommerce.ResponseDTOs.ItemResponseDTO;
import com.example.ECommerce.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/view/{productId}")
    public ResponseEntity viewItem(@PathVariable("productId") int productId){

        ItemResponseDTO itemResponseDTO;

        try{
            itemResponseDTO=itemService.viewItem(productId);
        }
        catch (Exception e){
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(itemResponseDTO,HttpStatus.ACCEPTED);
    }
}
