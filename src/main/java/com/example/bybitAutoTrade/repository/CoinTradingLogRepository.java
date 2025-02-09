package com.example.bybitAutoTrade.repository;

import com.example.bybitAutoTrade.entity.CoinTradingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinTradingLogRepository extends JpaRepository<CoinTradingLog, Long> {
}

