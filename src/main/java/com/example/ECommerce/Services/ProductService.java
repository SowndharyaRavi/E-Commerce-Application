package com.example.ECommerce.Services;

import com.example.ECommerce.Convertor.ProductConvertor;
import com.example.ECommerce.Enums.Category;
import com.example.ECommerce.Exception.SellerNotFoundException;
import com.example.ECommerce.Models.Product;
import com.example.ECommerce.Models.Seller;
import com.example.ECommerce.Repository.ProductRepository;
import com.example.ECommerce.Repository.SellerRepository;
import com.example.ECommerce.RequestDTOs.ProductRequestDTO;
import com.example.ECommerce.ResponseDTOs.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) throws SellerNotFoundException {
        Seller seller;

        try{
            seller=sellerRepository.findById(productRequestDTO.getSellerId()).get();
        }
        catch(Exception e){
            throw new SellerNotFoundException("Invalid seller id");
        }

        Product product= ProductConvertor.convertDTOtoProduct(productRequestDTO);
        product.setSeller(seller);

        seller.getProductList().add(product);

        sellerRepository.save(seller);

        ProductResponseDTO productResponseDTO=ProductConvertor.convertProductToDTO(product);

        return productResponseDTO;
    }

    public List<ProductResponseDTO> getAllProductsByCategory(Category category){

        List<ProductResponseDTO>productResponseDTOList=new ArrayList<>();

        List<Product>productList=productRepository.findAllByCategory(category);

        for(Product product:productList){
            ProductResponseDTO productResponseDTO=ProductConvertor.convertProductToDTO(product);
            productResponseDTOList.add(productResponseDTO);
        }

        return productResponseDTOList;
    }
}
