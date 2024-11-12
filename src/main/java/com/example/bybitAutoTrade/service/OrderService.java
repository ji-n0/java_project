package com.example.bybitAutoTrade.service;

import com.example.bybitAutoTrade.DTO.ApiKeySecretDTO;
import com.example.bybitAutoTrade.component.OrderComponent;
import com.example.bybitAutoTrade.config.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
    @Autowired
    private OrderComponent orderComponent;

    @Autowired
    private ApiConfig apiConfig;

    public void makeOrder(String skcType, String symbol, int buyLeverage, int sellLeverage, String side, int qty){
        ApiKeySecretDTO apiKeySecretDTO = new ApiKeySecretDTO();
        apiConfig.setApiKeySecret(skcType, apiKeySecretDTO);
        orderComponent.setLeverage(symbol, buyLeverage, sellLeverage, apiKeySecretDTO);
        orderComponent.makeOrder(symbol,side,qty, apiKeySecretDTO);
    }
}
