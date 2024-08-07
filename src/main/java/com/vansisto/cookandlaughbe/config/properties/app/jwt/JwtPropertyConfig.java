package com.vansisto.cookandlaughbe.config.properties.app.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.security.jwt")
public class JwtPropertyConfig {
    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationDays;
}
