package com.example.bybitAutoTrade.service;

import com.example.bybitAutoTrade.DTO.StrategyDTO;
import com.example.bybitAutoTrade.DTO.StrategyUsageDTO;
import com.example.bybitAutoTrade.entity.CoinStrategy;
import com.example.bybitAutoTrade.entity.CoinStrategyUsage;
import com.example.bybitAutoTrade.entity.Mbr;
import com.example.bybitAutoTrade.entity.compositeKey.CoinStrategyUsageId;
import com.example.bybitAutoTrade.repository.CoinStrategyRepository;
import com.example.bybitAutoTrade.repository.CoinStrategyUsageRepository;
import com.example.bybitAutoTrade.repository.MbrRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReactService {
    @Autowired
    private CoinStrategyUsageRepository coinStrategyUsageRepository;

    @Autowired
    private CoinStrategyRepository coinStrategyRepository;

    @Autowired
    private MbrRepository mbrRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<String> getAllMbrIds() {
        return mbrRepository.findAllMbrIds();
    }

    public List<StrategyUsageDTO> getStrategyUsageInfo(String memberId) {
        return coinStrategyUsageRepository.findByIdMemberId(memberId);
    }

    public StrategyDTO findStrategyById(String strategyId) {
        return coinStrategyRepository.findById(strategyId)
                .map(strategy -> new StrategyDTO(strategy.getStrategyName(),strategy.getTicker(), strategy.getStrategyParam(),
                        strategy.getLeverage(), strategy.getTakeProfitRate(), strategy.getStopLossRate()))
                .orElseThrow(() -> new IllegalArgumentException("전략 ID가 존재하지 않습니다."));
    }

    /**
     * 🔹 ActiveYn 값 업데이트
     */
    @Transactional
    public void updateStrategyUsage(List<StrategyUsageDTO> strategyUsageList) {
        for (StrategyUsageDTO dto : strategyUsageList) {
            CoinStrategyUsageId id = new CoinStrategyUsageId(dto.getMemberId(), dto.getStrategyId());

            // 기존 데이터 조회
            CoinStrategyUsage existingUsage = coinStrategyUsageRepository.findById(id).orElse(null);

            // 🔥 반드시 strategy를 가져와서 설정해야 함
            CoinStrategy strategy = coinStrategyRepository.findById(dto.getStrategyId())
                    .orElseThrow(() -> new IllegalArgumentException("전략을 찾을 수 없습니다."));

            if (existingUsage == null) {
                if ("Y".equals(dto.getActiveYn())) {
                    CoinStrategyUsage newUsage = new CoinStrategyUsage();
                    newUsage.setId(id);
                    newUsage.setStrategy(strategy); // ✅ 반드시 설정 필요
                    newUsage.setActiveYn(dto.getActiveYn());
                    coinStrategyUsageRepository.save(newUsage);
                }
            } else {
                existingUsage.setStrategy(strategy); // ✅ 기존 데이터도 strategy 설정 확인
                existingUsage.setActiveYn(dto.getActiveYn());
                coinStrategyUsageRepository.save(existingUsage);
            }
        }
    }

    @Transactional
    public Mbr registerMember(String id, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        Mbr member = new Mbr();
        member.setId(id);
        member.setPassword(encodedPassword);
        return mbrRepository.save(member);
    }
}
