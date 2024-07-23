package com.anas_practice.order_service.service;

import com.anas_practice.order_service.common.Payment;
import com.anas_practice.order_service.common.TransactionRequest;
import com.anas_practice.order_service.common.TransactionResponse;
import com.anas_practice.order_service.entity.Order;
import com.anas_practice.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest request){
        String response = "";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        Payment paymentResponse = restTemplate.postForObject("http://payment-service/payment/doPayment",payment,Payment.class);
        response = paymentResponse.getPaymentStatus().equals("success")?"Payment processing successfull!":"payment failed!!! Order added to cart";
        orderRepository.save(order);
        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }
}
