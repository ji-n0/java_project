package com.example.bybitAutoTrade.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class OrderRequestDTO {
    @NotNull(message = "symbol은 필수 값입니다.")
    private String symbol;
    private int buyLeverage = 0;
    private int sellLeverage = 0;
    private String side;
    private double qty;
    private double takeProfit = 0;
    private double stopLoss = 0;
    private String strategyId;
}
