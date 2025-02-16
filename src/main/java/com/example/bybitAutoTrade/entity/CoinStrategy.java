package com.example.bybitAutoTrade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "coin_strategy")
public class CoinStrategy{

    @Id
    @Column(name = "strategy_id", length = 5, nullable = false)
    private String strategyId;  // A0001, A0002 형태의 기본키

    @Column(name = "ticker", length = 20, nullable = false)
    private String ticker;  // 종목코드

    @Column(name = "strategy_nm", length = 200, nullable = false)
    private String strategyName;  // 전략명

    @Column(name = "strategy_param", columnDefinition = "json")
    private String strategyParam;  // JSON 데이터 (문자열로 저장)

    @Column(name = "leverage", nullable = false)
    @ColumnDefault("1")  // 기본값 1
    private Integer leverage;  // 레버리지 배율

    @Column(name = "tp_rate")
    private Float takeProfitRate;  // Take Profit 비율

    @Column(name = "sl_rate")
    private Float stopLossRate;  // Stop Loss 비율
}
