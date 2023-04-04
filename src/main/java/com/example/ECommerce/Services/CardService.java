package com.example.ECommerce.Services;

import com.example.ECommerce.Convertor.CardConverter;
import com.example.ECommerce.Exception.CustomerNotFoundException;
import com.example.ECommerce.Models.Card;
import com.example.ECommerce.Models.Customer;
import com.example.ECommerce.Repository.CustomerRepository;
import com.example.ECommerce.RequestDTOs.CardRequestDTO;
import com.example.ECommerce.ResponseDTOs.CardDTO;
import com.example.ECommerce.ResponseDTOs.CardResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    @Autowired
    CustomerRepository customerRepository;

    public CardResponseDTO addCard(CardRequestDTO cardRequestDTO) throws CustomerNotFoundException {
        Customer customer;
        try{
            customer=customerRepository.findById(cardRequestDTO.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Invalid customer id");
        }

        Card card= CardConverter.convertCTOToCard(cardRequestDTO);
        card.setCustomer(customer);

        customer.getCardList().add(card);

        customerRepository.save(customer);

        //for response DTO
        CardResponseDTO cardResponseDTO=new CardResponseDTO();
        cardResponseDTO.setName(customer.getName());

        //convert card to cardDTO
        List<CardDTO>cardDTOList=new ArrayList<>();
        for(Card card1:customer.getCardList()){
            CardDTO cardDTO=new CardDTO();
            cardDTO.setCardNo(card1.getCardNo());
            cardDTO.setCardType(card1.getCardType());

            cardDTOList.add(cardDTO);
        }

        cardResponseDTO.setCardDTOList(cardDTOList);
        return cardResponseDTO;
    }
}
