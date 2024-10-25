package com.portfolio.url_shortener.short_url.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.net.MalformedURLException;
import java.text.MessageFormat;


public record URL(@JsonValue String url) {
    private static final String DELIMITER = "/";

    @JsonCreator
    public URL {
        if (!isValid(url)) {
            throw new IllegalArgumentException(MessageFormat.format("URL isn''t valid: {0}", url));
        }
    }

    public URL(String baseUrl, String path) {
        this(baseUrl.concat(DELIMITER).concat(path));
    }




    private boolean isValid(String url) {
        try {
            new java.net.URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

}
