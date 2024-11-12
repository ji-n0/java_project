package com.example.bybitAutoTrade.component;

import com.bybit.api.client.domain.account.AccountType;
import com.bybit.api.client.domain.account.request.AccountDataRequest;
import com.bybit.api.client.service.BybitApiClientFactory;
import com.example.bybitAutoTrade.DTO.ApiKeySecretDTO;
import org.springframework.stereotype.Component;

@Component
public class AccountComponent {

    public Object getAccountBalance(String accountType, String coin, ApiKeySecretDTO apiKeySecretDTO) throws Exception {
        var accountRestClient = BybitApiClientFactory.newInstance(apiKeySecretDTO.getApiKey(), apiKeySecretDTO.getApiSecret()).newAccountRestClient();
        Object result = "";
        if(!"".equals(coin)){
            var setWalletRequest = AccountDataRequest.builder().accountType(AccountType.UNIFIED).coins(coin).build();
            result = accountRestClient.getWalletBalance(setWalletRequest);
        }
        else{
            var setWalletRequest = AccountDataRequest.builder().accountType(AccountType.UNIFIED).coin("").build();
            result = accountRestClient.getWalletBalance(setWalletRequest);
        }
        return result;
    }
}
