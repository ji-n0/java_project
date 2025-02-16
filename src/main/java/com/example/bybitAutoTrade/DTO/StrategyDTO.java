package com.example.bybitAutoTrade.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StrategyDTO {
    private String strategyNm;
    private String ticker;
    private String strategyParam;
    private Integer leverage;
    private Float tpRate;
    private Float slRate;
}
