package com.portfolio.url_shortener.short_url.application;

public class URLNotFound extends RuntimeException {

    public URLNotFound(String message) {
        super(message);
    }
}
