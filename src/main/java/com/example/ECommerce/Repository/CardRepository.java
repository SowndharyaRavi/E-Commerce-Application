package com.example.ECommerce.Repository;

import com.example.ECommerce.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
