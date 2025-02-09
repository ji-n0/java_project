package com.example.bybitAutoTrade.service;

import com.example.bybitAutoTrade.DTO.ApiKeySecretDTO;
import com.example.bybitAutoTrade.DTO.OrderRequestDTO;
import com.example.bybitAutoTrade.component.OrderComponent;
import com.example.bybitAutoTrade.config.ApiConfig;
import com.example.bybitAutoTrade.entity.CoinTradingLog;
import com.example.bybitAutoTrade.repository.CoinTradingLogRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrderComponent orderComponent;

    @Autowired
    private ApiConfig apiConfig;

    private final CoinTradingLogRepository coinTradingLogRepository;

    private final ObjectMapper objectMapper;

    public void enterPosition(String skcType, OrderRequestDTO orderRequestDTO) throws Exception{
        ApiKeySecretDTO apiKeySecretDTO = new ApiKeySecretDTO();
        Object orderResult;
        apiConfig.setApiKeySecret(skcType, apiKeySecretDTO);
        orderComponent.setLeverage(orderRequestDTO, apiKeySecretDTO);
        orderResult = orderComponent.makeOrder(orderRequestDTO, apiKeySecretDTO);
        String orderJsonResponse = objectMapper.writeValueAsString(orderResult);
        JsonNode rootNode = objectMapper.readTree(orderJsonResponse);
        if (rootNode.get("retCode").asInt() != 0) {
            throw new RuntimeException("주문 API 호출 실패: " + rootNode.get("retMsg").asText());
        }
        JsonNode orderResultJson = rootNode.path("result");
        System.out.println("현재 주문 id : " + orderResultJson.get("orderId").asText());
        saveOrderHist(orderResultJson.get("orderId").asText(), apiKeySecretDTO, skcType, orderRequestDTO.getStrategyId());
    }


    public Object saveOrderHist(String orderId, ApiKeySecretDTO apiKeySecretDTO, String skcType, String strategyId) throws Exception{
        Object result;
        result = orderComponent.saveOrderHist(orderId, apiKeySecretDTO);
        String jsonResponse = objectMapper.writeValueAsString(result);
        this.saveTradeOrderToDB(jsonResponse, skcType, strategyId);
        return result;
    }

    @Transactional
    public void saveTradeOrderToDB(String jsonResponse, String memberId, String strategyId) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            if (rootNode.get("retCode").asInt() != 0) {
                throw new RuntimeException("주문 체크 API 호출 실패: " + rootNode.get("retMsg").asText());
            }

            JsonNode orderList = rootNode.path("result").path("list");
            if (!orderList.isArray() || orderList.isEmpty()) {
                throw new RuntimeException("주문 데이터가 없습니다.");
            }

            for (JsonNode order : orderList) {
                CoinTradingLog coinTradingLog = CoinTradingLog.builder()
                        .memberId(memberId)
                        .strategyId(strategyId)
                        .orderQty(new BigDecimal(order.get("qty").asText()))
                        .orderType(order.get("side").asText())
                        .orderTime(convertTimestampToLocalDateTime(order.get("createdTime").asText()))
                        .filledQty(new BigDecimal(order.get("cumExecQty").asText()))
                        .avgFilledPrice(order.has("avgPrice") ? new BigDecimal(order.get("avgPrice").asText()) : null)
                        .filledTime(convertTimestampToLocalDateTime(order.get("updatedTime").asText()))
                        .build();

                coinTradingLogRepository.save(coinTradingLog);
                System.out.println("DB 저장 완료: " + coinTradingLog);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("JSON 처리 중 오류 발생: " + e.getMessage());
        }
    }

    private LocalDateTime convertTimestampToLocalDateTime(String timestamp) {
        long millis = Long.parseLong(timestamp);
        return Instant.ofEpochMilli(millis)
                .atZone(ZoneId.of("Asia/Seoul")) // ✅ 한국 시간(KST, UTC+9) 변환
                .toLocalDateTime();
    }

}
