package com.example.bybitAutoTrade.entity.compositeKey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

import lombok.*;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoinStrategyUsageId implements Serializable {

    @Column(name = "member_id", length = 1, nullable = false)
    private String memberId; // member 코드

    @Column(name = "strategy_id", length = 5, nullable = false)
    private String strategyId; // strategy 코드
}
