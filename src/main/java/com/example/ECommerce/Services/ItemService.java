package com.example.ECommerce.Services;

import com.example.ECommerce.Exception.ProductNotFoundException;
import com.example.ECommerce.Models.Item;
import com.example.ECommerce.Models.Product;
import com.example.ECommerce.Repository.ProductRepository;
import com.example.ECommerce.ResponseDTOs.ItemResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ProductRepository productRepository;

    public ItemResponseDTO viewItem(int productId) throws ProductNotFoundException {
        Product product;
        try{
            product=productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid product id");
        }

        Item item=Item.builder()
                .reqQuantity(0)
                .product(product).build();

        //product is viewed, so it'll become item,so map item to product
        product.setItem(item);

        productRepository.save(product);

        ItemResponseDTO itemResponseDTO=ItemResponseDTO.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .productStatus(product.getProductStatus()).build();

        return itemResponseDTO;
    }
}
