package com.example.ECommerce.ResponseDTOs;

import com.example.ECommerce.Enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private String cardNo;

    private CardType cardType;
}
