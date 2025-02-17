package com.example.bybitAutoTrade.repository;

import com.example.bybitAutoTrade.DTO.StrategyUsageDTO;
import com.example.bybitAutoTrade.entity.CoinStrategyUsage;
import com.example.bybitAutoTrade.entity.compositeKey.CoinStrategyUsageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CoinStrategyUsageRepository extends JpaRepository<CoinStrategyUsage, CoinStrategyUsageId> {
    @Query("SELECT new com.example.bybitAutoTrade.DTO.StrategyUsageDTO( " +
            "c.id.memberId, c.id.strategyId, c.activeYn) " +
            "FROM CoinStrategyUsage c " +
            "WHERE c.id.memberId = :memberId")
    List<StrategyUsageDTO> findByIdMemberId(@Param("memberId") String memberId);

    @Query("SELECT c.id.strategyId FROM CoinStrategyUsage c WHERE c.id.memberId = :memberId")
    List<String> findUsedStrategyIdsByMemberId(@Param("memberId") String memberId);

}
