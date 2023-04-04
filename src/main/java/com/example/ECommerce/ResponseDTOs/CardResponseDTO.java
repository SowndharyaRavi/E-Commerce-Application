package com.example.ECommerce.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDTO {

    private String name;

    private List<CardDTO> cardDTOList;
}
