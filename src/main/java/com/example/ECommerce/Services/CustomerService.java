package com.example.ECommerce.Services;

import com.example.ECommerce.Convertor.CustomerConverter;
import com.example.ECommerce.Models.Cart;
import com.example.ECommerce.Models.Customer;
import com.example.ECommerce.Repository.CustomerRepository;
import com.example.ECommerce.RequestDTOs.CustomerRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public String addCustomer(CustomerRequestDTO customerRequestDTO){
        Customer customer= CustomerConverter.convertDTOToCustomer(customerRequestDTO);
        //initialize cart to customer
        Cart cart=new Cart();
        cart.setCustomer(customer);
        cart.setCartTotal(0);

        customer.setCart(cart);

        customerRepository.save(customer);

        return "Customer has been added";
    }
}
