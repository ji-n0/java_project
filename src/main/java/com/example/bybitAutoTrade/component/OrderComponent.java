package com.example.bybitAutoTrade.component;

import com.bybit.api.client.domain.CategoryType;
import com.bybit.api.client.domain.position.request.PositionDataRequest;
import com.bybit.api.client.restApi.BybitApiAsyncTradeRestClient;
import com.bybit.api.client.service.BybitApiClientFactory;
import com.example.bybitAutoTrade.DTO.ApiKeySecretDTO;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class OrderComponent {
    public void setLeverage(String symbol, int buyLeverage, int sellLeverage, ApiKeySecretDTO apiKeySecretDTO){
        var positionClient = BybitApiClientFactory.newInstance(apiKeySecretDTO.getApiKey(), apiKeySecretDTO.getApiSecret()).newAsyncPositionRestClient();
        var setLeverageRequest = PositionDataRequest.builder().category(CategoryType.LINEAR).symbol(symbol).buyLeverage(Integer.toString(buyLeverage)).sellLeverage(Integer.toString(sellLeverage)).build();
        positionClient.setPositionLeverage(setLeverageRequest, System.out::println);
    }

    public String makeOrder(String symbol, String side, int qty, ApiKeySecretDTO apiKeySecretDTO){
        BybitApiClientFactory factory = BybitApiClientFactory.newInstance(apiKeySecretDTO.getApiKey(), apiKeySecretDTO.getApiSecret());
        BybitApiAsyncTradeRestClient client = factory.newAsyncTradeRestClient();
        String result = "SUCCESS";
        Map<String, Object> order =Map.of(
                "category", "linear",
                "symbol", symbol,
                "side", side,
                "orderType", "Market",
                "qty", Integer.toString(qty),
                "reduceOnly", false,
                "closeOnTrigger", false
        );
        client.createOrder(order, System.out::println);
        return result;
    }
}
