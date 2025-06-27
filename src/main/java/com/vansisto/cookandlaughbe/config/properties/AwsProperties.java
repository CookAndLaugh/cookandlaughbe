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
@ConfigurationProperties(prefix = "aws")
public class AwsProperties {
    private String region;
    private S3 s3;

    public record S3 (
        Buckets buckets,
        String host
    ) {
        public record Buckets (
            Content content
        ) {
            public record Content (
                String bucketName
            ) {}
        }
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setS3(S3 s3) {
        this.s3 = s3;
    }
}
