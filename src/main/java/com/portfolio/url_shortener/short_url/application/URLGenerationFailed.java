package com.portfolio.url_shortener.short_url.application;

public class URLGenerationFailed extends RuntimeException {

    public URLGenerationFailed(String message, Throwable cause) {
        super(message, cause);
    }
}

