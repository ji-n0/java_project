// AccountComponent.java

package com.example.bybitAutoTrade.component;

import com.bybit.api.client.domain.CategoryType;
import com.bybit.api.client.domain.account.AccountType;
import com.bybit.api.client.domain.account.request.AccountDataRequest;
import com.bybit.api.client.domain.position.request.PositionDataRequest;
import com.bybit.api.client.restApi.BybitApiAsyncTradeRestClient;
import com.bybit.api.client.service.BybitApiClientFactory;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.*;

@Component
public class AccountComponent {

    @Value("${bybit.api.key}")
    private String API_KEY;

    @Value("${bybit.api.secret}")
    private String API_SECRET;

    private final BybitApiClientFactory factory = BybitApiClientFactory.newInstance(API_KEY, API_SECRET);
    private final BybitApiAsyncTradeRestClient client = factory.newAsyncTradeRestClient();


    public String getAccountBalance(String accountType, String coin) throws Exception {
        var accountRestClient = BybitApiClientFactory.newInstance(API_KEY, API_SECRET).newAccountRestClient();
        String result = "";
        if(!"".equals(coin)){
            var setWalletRequest = AccountDataRequest.builder().accountType(AccountType.UNIFIED).coins(coin).build();
            result = accountRestClient.getWalletBalance(setWalletRequest).toString();
        }
        else{
            var setWalletRequest = AccountDataRequest.builder().accountType(AccountType.UNIFIED).coin("").build();
            result = accountRestClient.getWalletBalance(setWalletRequest).toString();
        }
        return result;
    }
}
