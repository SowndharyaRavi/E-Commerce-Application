package com.example.ECommerce.Controllers;

import com.example.ECommerce.RequestDTOs.CardRequestDTO;
import com.example.ECommerce.ResponseDTOs.CardResponseDTO;
import com.example.ECommerce.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/addCard")
    public ResponseEntity addCard(@RequestBody CardRequestDTO cardRequestDTO){

        CardResponseDTO cardResponseDTO;

        try {
            cardResponseDTO=cardService.addCard(cardRequestDTO);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(cardResponseDTO,HttpStatus.ACCEPTED);
    }

    // remove card;

    //remove all card for a particular customer id;
}
