package com.example.bybitAutoTrade.controller;

import com.example.bybitAutoTrade.DTO.OrderRequestDTO;
import com.example.bybitAutoTrade.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order/enterPosition")
    public Object enterPosition(@RequestParam(required = true) String skgType,@Valid @ModelAttribute OrderRequestDTO orderRequestDTO) {
        Object result = null;
        try {
            orderService.enterPosition(skgType, orderRequestDTO);
        }catch (Exception e){
            result = e.getMessage();
        }
        return result;
    }
}
