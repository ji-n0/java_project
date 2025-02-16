package com.example.bybitAutoTrade.repository;

import com.example.bybitAutoTrade.entity.CoinStrategy;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinStrategyRepository extends JpaRepository<CoinStrategy, String> {
}

