package com.example.ECommerce.Services;

import com.example.ECommerce.Enums.ProductStatus;
import com.example.ECommerce.Exception.CustomerNotFoundException;
import com.example.ECommerce.Exception.ProductNotFoundException;
import com.example.ECommerce.Models.*;
import com.example.ECommerce.Repository.CustomerRepository;
import com.example.ECommerce.Repository.ProductRepository;
import com.example.ECommerce.RequestDTOs.OrderRequestDTO;
import com.example.ECommerce.ResponseDTOs.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    JavaMailSender emailSender;

    public String addToCart(OrderRequestDTO orderRequestDTO)throws Exception{
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

        Cart cart=customer.getCart();
        int newCost= cart.getCartTotal()+ (orderRequestDTO.getRequiredQuantity()* product.getPrice());
        cart.setCartTotal(newCost);

        //add item to cur cart
        Item item=new Item();
        item.setReqQuantity(orderRequestDTO.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.getItemList().add(item);

        customerRepository.save(customer);

        return "Item has been added to the cart";
    }

    public List<OrderResponseDTO> checkOutCart(int customerId)throws Exception{
        Customer customer;
        try{
            customer=customerRepository.findById(customerId).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer id");
        }

        List<OrderResponseDTO>orderResponseDTOList = new ArrayList<>();
        int totalCost=customer.getCart().getCartTotal();
        Cart cart=customer.getCart();
        for(Item item:cart.getItemList()){
            Ordered order = new Ordered();
            order.setTotalCost(item.getReqQuantity()*item.getProduct().getPrice());
            order.setDeliveryCharge(0);
            order.setCustomer(customer);
            order.getItemList().add(item);

            Card card = customer.getCardList().get(0);
            String cardNo = "";
            for(int i=0;i<card.getCardNo().length()-4;i++)
                cardNo += 'X';
            cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
            order.setCardUsedForPayment(cardNo);

            int leftQuantity = item.getProduct().getQuantity()-item.getReqQuantity();
            if(leftQuantity<=0)
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            item.getProduct().setQuantity(leftQuantity);

            customer.getOrderList().add(order);

            // prepare response dto
            OrderResponseDTO orderResponseDto = OrderResponseDTO.builder()
                    .productName(item.getProduct().getName())
                    .orderDate(order.getOrderDate())
                    .quantityOrdered(item.getReqQuantity())
                    .cardUsedForPayment(cardNo)
                    .ItemPrice(item.getProduct().getPrice())
                    .totalCost(order.getTotalCost())
                    .deliveryCharge(0)
                    .build();

            orderResponseDTOList.add(orderResponseDto);
        }

        cart.setItemList(new ArrayList<>());
        cart.setCartTotal(0);
        customerRepository.save(customer);

        //send an email
        String text="Congrats! your order with total value "+totalCost+" has been placed";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("formy***useandproj@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Order placed");
        message.setText(text);
        emailSender.send(message);

        return orderResponseDTOList;
    }
}
