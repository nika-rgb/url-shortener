package com.portfolio.url_shortener.short_url.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.Collectors;


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

    public URL(String baseUrl, String ... pathParts) {
        this(
                Arrays.stream(pathParts)
                        .collect(Collectors.joining(DELIMITER, baseUrl + DELIMITER, ""))
        );
    }

    public URL(URL otherUrl) {
        this(otherUrl.url());
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
