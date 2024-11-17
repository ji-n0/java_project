package com.example.bybitAutoTrade.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
public class OrderRequestDTO {
    @NotNull(message = "symbol은 필수 값입니다.")
    private String symbol;
    private int buyLeverage = 0;
    private int sellLeverage = 0;
    private String side;
    private int qty;
    private double takeProfit = 0;
    private double lossStop = 0;
}
