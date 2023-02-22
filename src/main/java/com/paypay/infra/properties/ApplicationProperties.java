package com.paypay.infra.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("application")
public class ApplicationProperties {
    private String title;
    private String version;

    @Data
    @ConfigurationProperties("application.cache")
    public static class Cache {
        private Long defaultTimeout;
    }
}
