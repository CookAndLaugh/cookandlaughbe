package com.vansisto.cookandlaughbe.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Accessors(fluent = true)
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private Domain domain;
    private Security security;

    public record Domain(
            String root
    ) {}

    public record Security(
            Jwt jwt,
            Registration registration
    ) {
        public record Jwt(
                String secretKey,
                String tokenPrefix,
                Integer tokenExpirationDays
        ) {}

        public record Registration(
                ActivationCode activationCode
        ) {
            public record ActivationCode(
                    Integer length,
                    Integer expirationMinutes,
                    String confirmationUrl,
                    String loginRedirectSuffixUrl,
                    String registrationRedirectSuffixUrl
            ) {}
        }
    }
}
