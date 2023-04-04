package com.example.ECommerce.Convertor;

import com.example.ECommerce.Models.Card;
import com.example.ECommerce.RequestDTOs.CardRequestDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardConverter {

    public static Card convertCTOToCard(CardRequestDTO cardRequestDTO){
        Card card=Card.builder()
                .cardNo(cardRequestDTO.getCardNo())
                .cardType(cardRequestDTO.getCardType())
                .cvv(cardRequestDTO.getCvv()).build();

        return card;
    }
}
