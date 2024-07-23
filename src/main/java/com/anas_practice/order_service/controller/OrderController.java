package com.anas_practice.order_service.controller;

import com.anas_practice.order_service.common.Payment;
import com.anas_practice.order_service.common.TransactionRequest;
import com.anas_practice.order_service.common.TransactionResponse;
import com.anas_practice.order_service.entity.Order;
//import datadog.trace.api.DDTags;
//import datadog.trace.api.Trace;
import com.anas_practice.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;


    @PostMapping("/bookOrder")
//    @Trace(operationName = "bookOrder.request")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){

        return service.saveOrder(request);

    }
}
