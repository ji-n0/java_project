package com.example.bybitAutoTrade.component;

import com.bybit.api.client.domain.CategoryType;
import com.bybit.api.client.domain.TradeOrderType;
import com.bybit.api.client.domain.TriggerBy;
import com.bybit.api.client.domain.position.TpslMode;
import com.bybit.api.client.domain.position.request.PositionDataRequest;
import com.bybit.api.client.domain.trade.PositionIdx;
import com.bybit.api.client.restApi.BybitApiAsyncTradeRestClient;
import com.bybit.api.client.service.BybitApiClientFactory;
import com.example.bybitAutoTrade.DTO.ApiKeySecretDTO;
import com.example.bybitAutoTrade.DTO.OrderRequestDTO;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderComponent {
    public void setLeverage(OrderRequestDTO orderRequestDTO, ApiKeySecretDTO apiKeySecretDTO){
        var positionClient = BybitApiClientFactory.newInstance(apiKeySecretDTO.getApiKey(), apiKeySecretDTO.getApiSecret()).newAsyncPositionRestClient();

        if(orderRequestDTO.getBuyLeverage() == 0){
            orderRequestDTO.setBuyLeverage(orderRequestDTO.getSellLeverage());
        }
        if(orderRequestDTO.getSellLeverage() == 0){
            orderRequestDTO.setSellLeverage(orderRequestDTO.getBuyLeverage());
        }

        var setLeverageRequest = PositionDataRequest
                .builder()
                .category(CategoryType.LINEAR)
                .symbol(orderRequestDTO.getSymbol())
                .buyLeverage(Integer.toString(orderRequestDTO.getBuyLeverage()))
                .sellLeverage(Integer.toString(orderRequestDTO.getSellLeverage()))
                .build();

        var tradingStopRequest = PositionDataRequest
                .builder()
                .category(CategoryType.LINEAR)
                .symbol(orderRequestDTO.getSymbol())
                .tpslMode(TpslMode.FULL)
                .positionIdx(PositionIdx.ONE_WAY_MODE)
                .tpTriggerBy(TriggerBy.MARK_PRICE)
                .slTriggerBy(TriggerBy.MARK_PRICE)
                .tpOrderType(TradeOrderType.MARKET)
                .takeProfit(Double.toString(orderRequestDTO.getTakeProfit()))
                .stopLoss(Double.toString(orderRequestDTO.getStopLoss()))
                .build();

        positionClient.setTradingStop(tradingStopRequest, System.out::println);
        positionClient.setPositionLeverage(setLeverageRequest, System.out::println);
    }

    public String makeOrder(OrderRequestDTO orderRequestDTO, ApiKeySecretDTO apiKeySecretDTO){
        BybitApiClientFactory factory = BybitApiClientFactory.newInstance(apiKeySecretDTO.getApiKey(), apiKeySecretDTO.getApiSecret());
        BybitApiAsyncTradeRestClient client = factory.newAsyncTradeRestClient();
        String result = "SUCCESS";
        Map<String, Object> order =Map.of(
                "category", "linear",
                "symbol", orderRequestDTO.getSymbol(),
                "side", orderRequestDTO.getSide(),
                "orderType", "Market",
                "qty", Double.toString(orderRequestDTO.getQty()),
                "reduceOnly", false,
                "closeOnTrigger", false
        );

        client.createOrder(order, System.out::println);
        return result;
    }
}
