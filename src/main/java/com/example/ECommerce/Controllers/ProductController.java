package com.example.ECommerce.Controllers;

import com.example.ECommerce.Enums.Category;
import com.example.ECommerce.RequestDTOs.ProductRequestDTO;
import com.example.ECommerce.ResponseDTOs.ProductResponseDTO;
import com.example.ECommerce.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO productResponseDTO;
       try{
         productResponseDTO = productService.addProduct(productRequestDTO);
       }
       catch (Exception e){
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
       }

       return new ResponseEntity<>(productResponseDTO,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/category/{category}")
    public List<ProductResponseDTO> getAllProductsByCategory(@PathVariable("category") Category category){
        return productService.getAllProductsByCategory(category);
    }
}
