package com.example.bybitAutoTrade.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StrategyUsageDTO {
    private String memberId;
    private String strategyId;
    private String activeYn;
}
