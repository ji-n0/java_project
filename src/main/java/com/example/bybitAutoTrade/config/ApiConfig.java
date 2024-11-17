package com.example.bybitAutoTrade.config;

import com.example.bybitAutoTrade.DTO.ApiKeySecretDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
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

    public void setApiKeySecret(String type, ApiKeySecretDTO apiKeySecretDTO) {
        switch (type) {
            case "s":
                apiKeySecretDTO.setApiKey(API_KEY_SON);
                apiKeySecretDTO.setApiSecret(API_SECRET_SON);
                break;
            case "k":
                apiKeySecretDTO.setApiKey(API_KEY_KWON);
                apiKeySecretDTO.setApiSecret(API_SECRET_KWON);
                break;
            case "c":
                apiKeySecretDTO.setApiKey(API_KEY_CHOI);
                apiKeySecretDTO.setApiSecret(API_SECRET_CHOI);
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type + ". Valid types are 's', 'k', or 'c'.");
        }
    }
}
