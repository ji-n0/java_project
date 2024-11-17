// AccountController.java

package com.example.bybitAutoTrade.controller;

import com.bybit.api.client.domain.CategoryType;
import com.bybit.api.client.domain.account.AccountType;
import com.bybit.api.client.domain.account.request.AccountDataRequest;
import com.bybit.api.client.domain.position.request.PositionDataRequest;
import com.bybit.api.client.restApi.BybitApiAsyncTradeRestClient;
import com.bybit.api.client.service.BybitApiClientFactory;
import com.example.bybitAutoTrade.DTO.BalanceRequestDTO;
import com.example.bybitAutoTrade.service.AccountService;
import com.example.bybitAutoTrade.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account/balance")
    public Object getAccountBalance(@RequestParam(required = true) String skgType, @ModelAttribute BalanceRequestDTO balanceRequestDTO){
        Object result = null;
        try{
            result =  accountService.getAccountBalance(skgType, balanceRequestDTO);
        }catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }
}
