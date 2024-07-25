package com.vansisto.cookandlaughbe.config.properties.app.registration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.security.registration.activation-code")
public class ActivationCodePropertyConfig {
    private Integer expirationMinutes;
    private String confirmationUrl;
}
