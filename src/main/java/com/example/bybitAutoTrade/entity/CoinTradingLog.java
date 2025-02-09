package com.example.bybitAutoTrade.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "coin_trading_log")
public class CoinTradingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ AUTO_INCREMENT 적용
    @Column(name = "trading_seq", nullable = false)
    private Integer tradingSeq; // 거래 시퀀스 (PK)

    @Column(name = "member_id", length = 1, nullable = false)
    private String memberId; // member 코드

    @Column(name = "strategy_id", length = 5, nullable = false)
    private String strategyId; // strategy 코드

    @Column(name = "order_qty", precision = 18, scale = 8, nullable = false)
    private BigDecimal orderQty; // 주문 수량

    @Column(name = "order_type", length = 10, nullable = false)
    private String orderType; // 주문 형태

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime; // 주문 시간

    @Column(name = "filled_qty", precision = 18, scale = 8, nullable = false)
    private BigDecimal filledQty = BigDecimal.ZERO; // 체결 수량 (기본값 0)

    @Column(name = "avg_filled_price", precision = 18, scale = 8)
    private BigDecimal avgFilledPrice; // 체결 평균 가격

    @Column(name = "filled_time")
    private LocalDateTime filledTime; // 체결 시간
}
