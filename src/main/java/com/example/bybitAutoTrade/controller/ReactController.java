package com.example.bybitAutoTrade.controller;

import com.example.bybitAutoTrade.DTO.StrategyDTO;
import com.example.bybitAutoTrade.DTO.StrategyUsageDTO;
import com.example.bybitAutoTrade.service.ReactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/react")
@CrossOrigin(origins = "*")
public class ReactController {
    @Autowired
    private ReactService reactService;

    @GetMapping("/mbr/ids")
    public List<String> getMbrIds() {
        return reactService.getAllMbrIds();
    }

    @GetMapping("/mbr/strategyUsageInfo")
    public List<StrategyUsageDTO> getStrategyUsageInfo(@RequestParam String memberId) {
        return reactService.getStrategyUsageInfo(memberId);
    }

    @PutMapping("/mbr/updateStrategyUsage")
    public ResponseEntity<String> updateStrategyUsage(@RequestBody List<StrategyUsageDTO> updateRequests) {
        System.out.println("📢 업데이트 요청 수: " + updateRequests.size());

        if (updateRequests.isEmpty()) {
            return ResponseEntity.badRequest().body("⚠️ 업데이트할 데이터가 없습니다.");
        }

        reactService.updateStrategyUsage(updateRequests);
        return ResponseEntity.ok("✅ 전략 Active 값 저장 완료!");
    }

    @GetMapping("/strategy/details")
    public ResponseEntity<StrategyDTO> getStrategyDetails(@RequestParam String strategyId) {
        StrategyDTO strategy = reactService.findStrategyById(strategyId);
        return ResponseEntity.ok(strategy);
    }
}
