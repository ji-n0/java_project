package com.example.bybitAutoTrade.service;

import com.example.bybitAutoTrade.component.AccountComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountComponent accountComponent;

    public String getAccountBalance(String accountType, String coin) throws Exception {
        return accountComponent.getAccountBalance(accountType, coin);
    }
}
