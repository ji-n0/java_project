// AccountController.java

package com.example.bybitAutoTrade.controller;

import com.bybit.api.client.domain.CategoryType;
import com.bybit.api.client.domain.account.AccountType;
import com.bybit.api.client.domain.account.request.AccountDataRequest;
import com.bybit.api.client.domain.position.request.PositionDataRequest;
import com.bybit.api.client.restApi.BybitApiAsyncTradeRestClient;
import com.bybit.api.client.service.BybitApiClientFactory;
import com.example.bybitAutoTrade.service.AccountService;
import com.example.bybitAutoTrade.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account/balance")
    public Object getAccountBalance(@RequestParam(required = true) String skgType, @RequestParam(required = false) String accountType, @RequestParam(required = false) String coin) throws Exception {
        return accountService.getAccountBalance(skgType, accountType, coin);
    }
}
