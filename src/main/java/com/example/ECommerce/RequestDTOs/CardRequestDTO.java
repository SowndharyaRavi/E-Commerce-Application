package com.example.ECommerce.RequestDTOs;

import com.example.ECommerce.Enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDTO {
    private int customerId;

    private String cardNo;

    private int cvv;

    private CardType cardType;
}
