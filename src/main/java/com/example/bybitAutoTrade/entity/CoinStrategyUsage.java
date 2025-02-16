package com.example.bybitAutoTrade.entity;

import com.example.bybitAutoTrade.entity.compositeKey.CoinStrategyUsageId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "coin_strategy_usage")
public class CoinStrategyUsage {

    @EmbeddedId
    private CoinStrategyUsageId id;  // 복합 키

    @ManyToOne
    @MapsId("strategyId")
    @JoinColumn(name = "strategy_id", updatable = false)
    private CoinStrategy strategy;  // CoinStrategy 엔티티와 관계 설정

    @Column(name = "active_yn", length = 1)
    private String activeYn;  // 사용 여부 (Y/N)

    public CoinStrategyUsage(CoinStrategyUsageId id, String activeYn){
        this.id = id;
        this.activeYn = activeYn;
    }

    public boolean isActive() {
        return "Y".equalsIgnoreCase(this.activeYn);
    }
}
