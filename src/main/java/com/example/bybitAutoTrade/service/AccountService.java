package com.example.bybitAutoTrade.service;

import com.example.bybitAutoTrade.DTO.ApiKeySecretDTO;
import com.example.bybitAutoTrade.DTO.BalanceRequestDTO;
import com.example.bybitAutoTrade.component.AccountComponent;
import com.example.bybitAutoTrade.config.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountComponent accountComponent;

    @Autowired
    private ApiConfig apiConfig;

    public Object getAccountBalance(String skgType, BalanceRequestDTO balanceRequestDTO) throws Exception {
        ApiKeySecretDTO apiKeySecretDTO = new ApiKeySecretDTO();
        apiConfig.setApiKeySecret(skgType, apiKeySecretDTO);
        return accountComponent.getAccountBalance(balanceRequestDTO, apiKeySecretDTO);
    }

    public Object getPositionInfo(String skgType, BalanceRequestDTO balanceRequestDTO) throws Exception {
        ApiKeySecretDTO apiKeySecretDTO = new ApiKeySecretDTO();
        apiConfig.setApiKeySecret(skgType, apiKeySecretDTO);
        return accountComponent.getPositionInfo(balanceRequestDTO, apiKeySecretDTO);
    }
}
