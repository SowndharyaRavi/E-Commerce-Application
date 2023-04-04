package com.example.ECommerce.Controllers;

import com.example.ECommerce.Models.Customer;
import com.example.ECommerce.RequestDTOs.CustomerRequestDTO;
import com.example.ECommerce.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/addCustomer")
    public String addCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
        return customerService.addCustomer(customerRequestDTO);
    }

    //get customer by id;

    //view all customers;

    //delete customer by id;

    //get customer by email;

    //update customer;
}
