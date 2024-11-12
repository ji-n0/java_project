package com.example.bybitAutoTrade.config;

import com.example.bybitAutoTrade.DTO.ApiKeySecretDTO;
import org.springframework.beans.factory.annotation.Value;

public class ApiConfig {
    @Value("${bybit.api.key.s}")
    private String API_KEY_SON;
    @Value("${bybit.api.secret.s}")
    private String API_SECRET_SON;

    @Value("${bybit.api.key.k}")
    private String API_KEY_KWON;
    @Value("${bybit.api.secret.k}")
    private String API_SECRET_KWON;

    @Value("${bybit.api.key.c}")
    private String API_KEY_CHOI;
    @Value("${bybit.api.secret.c}")
    private String API_SECRET_CHOI;

    public boolean setApiKeySecret(String type, ApiKeySecretDTO apiKeySecretDTO){
        boolean result = false;
        if("s".equals(type)){
            apiKeySecretDTO.setApiKey(API_KEY_SON);
            apiKeySecretDTO.setApiSecret(API_SECRET_SON);
            result = true;
        }else if("k".equals(type)){
            apiKeySecretDTO.setApiKey(API_KEY_KWON);
            apiKeySecretDTO.setApiSecret(API_SECRET_KWON);
            result = true;
        }else if("c".equals(type)){
            apiKeySecretDTO.setApiKey(API_KEY_CHOI);
            apiKeySecretDTO.setApiSecret(API_SECRET_CHOI);
            result = true;
        }
        return result;
    }
}
