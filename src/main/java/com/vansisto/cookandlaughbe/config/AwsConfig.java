package com.vansisto.cookandlaughbe.config;

import com.vansisto.cookandlaughbe.config.properties.AwsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@RequiredArgsConstructor
@Configuration
public class AwsConfig {
    private final AwsProperties awsProperties;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .endpointOverride(URI.create(awsProperties.s3().host()))
                .region(Region.of(awsProperties.region()))
                .forcePathStyle(true)
                .build();
    }
}
