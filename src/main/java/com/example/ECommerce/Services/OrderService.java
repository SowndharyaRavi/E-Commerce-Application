package com.example.ECommerce.Services;

import com.example.ECommerce.Enums.ProductStatus;
import com.example.ECommerce.Exception.CustomerNotFoundException;
import com.example.ECommerce.Exception.ProductNotFoundException;
import com.example.ECommerce.Models.*;
import com.example.ECommerce.Repository.CustomerRepository;
import com.example.ECommerce.Repository.ItemRepository;
import com.example.ECommerce.Repository.ProductRepository;
import com.example.ECommerce.RequestDTOs.OrderRequestDTO;
import com.example.ECommerce.ResponseDTOs.ItemResponseDTO;
import com.example.ECommerce.ResponseDTOs.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;

@Service
public class OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    JavaMailSender emailSender;

    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) throws Exception {
        Customer customer;
        try{
            customer=customerRepository.findById(orderRequestDTO.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer id");
        }

        Product product;
        try{
            product=productRepository.findById(orderRequestDTO.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid product id");
        }

        if(product.getQuantity() < orderRequestDTO.getRequiredQuantity()){
            throw new InsufficientResourcesException("Sorry,Stocks are less than the required quantity");
        }

        Ordered order = new Ordered();
        order.setTotalCost(orderRequestDTO.getRequiredQuantity()* product.getPrice());
        order.setDeliveryCharge(40);
        Card card = customer.getCardList().get(0);
        String cardNo = "";
        for(int i=0;i<card.getCardNo().length()-4;i++)
            cardNo += 'X';
        cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
        order.setCardUsedForPayment(cardNo);

        Item item = new Item();
        item.setReqQuantity(orderRequestDTO.getRequiredQuantity());
        item.setProduct(product);
        item.setOrder(order);

        order.getItemList().add(item);
        order.setCustomer(customer);

        int leftQuantity = product.getQuantity()-orderRequestDTO.getRequiredQuantity();
        if(leftQuantity<=0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);

        customer.getOrderList().add(order);
        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrderList().get(savedCustomer.getOrderList().size()-1);

        //responseDto
        OrderResponseDTO orderResponseDTO=OrderResponseDTO.builder()
                .productName(product.getName())
                .orderDate(savedOrder.getOrderDate())
                .quantityOrdered(orderRequestDTO.getRequiredQuantity())
                .cardUsedForPayment(cardNo)
                .ItemPrice(product.getPrice())
                .totalCost(order.getTotalCost())
                .deliveryCharge(40)
                .build();

        //send an email
//        String text="Congrats! your order with total value "+order.getTotalCost()+" has been placed";
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("formy***useandproj@gmail.com");
//        message.setTo(customer.getEmail());
//        message.setSubject("Order placed");
//        message.setText(text);
//        emailSender.send(message);

        return orderResponseDTO;
    }
}
