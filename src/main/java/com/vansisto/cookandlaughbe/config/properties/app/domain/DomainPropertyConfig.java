package com.vansisto.cookandlaughbe.config.properties.app.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.domain")
public class DomainPropertyConfig {
    private String root;
}
