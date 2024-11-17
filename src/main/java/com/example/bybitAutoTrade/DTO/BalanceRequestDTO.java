package com.example.bybitAutoTrade.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
public class BalanceRequestDTO {
    private String accountType;
    private String coin;
}
