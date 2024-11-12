package com.example.bybitAutoTrade.controller;

import com.example.bybitAutoTrade.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order/setPosition")
    public void makeOrder(@RequestParam(required = true) String skgType, @RequestParam(required = true) String symbol, @RequestParam(required = false) int buyLeverage
            , @RequestParam(required = false) int sellLeverage, @RequestParam(required = true) String side, @RequestParam(required = false) int qty) {
        orderService.makeOrder(skgType, symbol, buyLeverage, sellLeverage, side, qty);
    }
}
