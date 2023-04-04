package com.example.ECommerce.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int reqQuantity;

    @ManyToOne
    @JoinColumn
    private Cart cart;

    @OneToOne
    @JoinColumn
    private Product product;

    @ManyToOne
    @JoinColumn
    private Ordered order;

}
