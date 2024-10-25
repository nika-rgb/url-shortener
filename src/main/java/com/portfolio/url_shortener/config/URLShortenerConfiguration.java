package com.portfolio.url_shortener.config;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "url-shortener")
public class URLShortenerConfiguration {

    @NotNull
    private String protocol;
    @NotNull
    private String host;
    @NotNull
    @URL
    private String baseUrl;
    @NotNull
    @Positive
    private Integer generatedUrlLength;
    @NotNull
    @Positive
    private Integer customUrlMaxLength;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getGeneratedUrlLength() {
        return generatedUrlLength;
    }

    public void setGeneratedUrlLength(Integer generatedUrlLength) {
        this.generatedUrlLength = generatedUrlLength;
    }

    public Integer getCustomUrlMaxLength() {
        return customUrlMaxLength;
    }

    public void setCustomUrlMaxLength(Integer customUrlMaxLength) {
        this.customUrlMaxLength = customUrlMaxLength;
    }
}
