package com.vansisto.cookandlaughbe.security;

public enum AntEndpoints {
    WHITE_LIST(
            "/healthcheck",
            "/auth/**"
    ),
    SWAGGER(
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    );

    private final String[] endpoints;

    AntEndpoints(String... endpoints) {
        this.endpoints = endpoints;
    }

    public String[] get() {
        return endpoints;
    }
}
