package com.example.bybitAutoTrade.component;

import com.bybit.api.client.domain.CategoryType;
import com.bybit.api.client.domain.TradeOrderType;
import com.bybit.api.client.domain.TriggerBy;
import com.bybit.api.client.domain.account.AccountType;
import com.bybit.api.client.domain.account.request.AccountDataRequest;
import com.bybit.api.client.domain.position.TpslMode;
import com.bybit.api.client.domain.position.request.PositionDataRequest;
import com.bybit.api.client.domain.trade.PositionIdx;
import com.bybit.api.client.service.BybitApiClientFactory;
import com.example.bybitAutoTrade.DTO.ApiKeySecretDTO;
import com.example.bybitAutoTrade.DTO.BalanceRequestDTO;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AccountComponent {
    public Object getAccountBalance(BalanceRequestDTO balanceRequestDTO, ApiKeySecretDTO apiKeySecretDTO) throws Exception {
        var accountRestClient = BybitApiClientFactory.newInstance(apiKeySecretDTO.getApiKey(), apiKeySecretDTO.getApiSecret()).newAccountRestClient();
        Object result = "";
        if (balanceRequestDTO.getCoin() != null && !"".equals(balanceRequestDTO.getCoin())) {
            var setWalletRequest = AccountDataRequest.builder().accountType(AccountType.UNIFIED).coins(balanceRequestDTO.getCoin()).build();
            result = accountRestClient.getWalletBalance(setWalletRequest);
        } else {
            var setWalletRequest = AccountDataRequest.builder().accountType(AccountType.UNIFIED).coin("").build();
            result = accountRestClient.getWalletBalance(setWalletRequest);
        }
        return result;
    }

    public Object getPositionInfo(BalanceRequestDTO balanceRequestDTO, ApiKeySecretDTO apiKeySecretDTO) throws Exception {
        var positionClient = BybitApiClientFactory.newInstance(apiKeySecretDTO.getApiKey(), apiKeySecretDTO.getApiSecret()).newAsyncPositionRestClient();
        CompletableFuture<Object> future = new CompletableFuture<>();
        Object result = "";
        if(balanceRequestDTO.getCoin() != null && !"".equals(balanceRequestDTO.getCoin())){
            var setWalletRequest = PositionDataRequest
                    .builder()
                    .category(CategoryType.LINEAR)
                    .symbol(balanceRequestDTO.getCoin())
                    .build();
            positionClient.getPositionInfo(setWalletRequest, response -> {
                System.out.println("현재 진입 포지션 : " + response);
                future.complete(response);
            });
        }else{
            var setWalletRequest = PositionDataRequest
                    .builder()
                    .category(CategoryType.LINEAR)
                    .build();
            positionClient.getPositionInfo(setWalletRequest, response -> {
                future.complete(response);
            });
        }
        result = future.get();
        return result;
    }
}
