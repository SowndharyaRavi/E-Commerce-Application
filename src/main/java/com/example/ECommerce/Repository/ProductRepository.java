package com.example.ECommerce.Repository;

import com.example.ECommerce.Enums.Category;
import com.example.ECommerce.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


    List<Product> findAllByCategory(Category category);
}
