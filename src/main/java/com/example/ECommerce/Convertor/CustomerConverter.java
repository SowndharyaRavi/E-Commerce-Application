package com.example.ECommerce.Convertor;

import com.example.ECommerce.Models.Customer;
import com.example.ECommerce.RequestDTOs.CustomerRequestDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConverter {

    public static Customer convertDTOToCustomer(CustomerRequestDTO customerRequestDTO){
        Customer customer=Customer.builder().
                name(customerRequestDTO.getName())
                .age(customerRequestDTO.getAge())
                .mobNo(customerRequestDTO.getMobNo())
                .email(customerRequestDTO.getEmail()).build();

        return customer;
    }
}
