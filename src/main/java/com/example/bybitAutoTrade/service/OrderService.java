package com.example.bybitAutoTrade.service;

import com.example.bybitAutoTrade.DTO.ApiKeySecretDTO;
import com.example.bybitAutoTrade.DTO.OrderRequestDTO;
import com.example.bybitAutoTrade.component.OrderComponent;
import com.example.bybitAutoTrade.config.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderComponent orderComponent;

    @Autowired
    private ApiConfig apiConfig;

    public void enterPosition(String skcType, OrderRequestDTO orderRequestDTO) throws Exception{
        ApiKeySecretDTO apiKeySecretDTO = new ApiKeySecretDTO();
        apiConfig.setApiKeySecret(skcType, apiKeySecretDTO);
        orderComponent.setLeverage(orderRequestDTO, apiKeySecretDTO);
        orderComponent.makeOrder(orderRequestDTO, apiKeySecretDTO);
    }

}
