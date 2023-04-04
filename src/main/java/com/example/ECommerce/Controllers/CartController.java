package com.example.ECommerce.Controllers;

import com.example.ECommerce.RequestDTOs.OrderRequestDTO;
import com.example.ECommerce.ResponseDTOs.OrderResponseDTO;
import com.example.ECommerce.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/addToCart")
    public String addToCart(@RequestBody OrderRequestDTO orderRequestDTO){
        String response="";
        try{
            response=cartService.addToCart(orderRequestDTO);
        }
        catch (Exception e){
            return e.getMessage();
        }

        return response;
    }

    @PostMapping("/checkOut/{customerId}")
    public ResponseEntity checkOutCart(@PathVariable("customerId") int customerId){
        List<OrderResponseDTO>orderResponseDTOList;
        try {
            orderResponseDTOList=cartService.checkOutCart(customerId);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(orderResponseDTOList,HttpStatus.ACCEPTED);
    }
}
