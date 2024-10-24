package com.portfolio.url_shortener.short_url.domain;

import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;

import java.text.MessageFormat;

public record URL(String url) {

    private static final URLValidator urlValidator = new URLValidator();

    public URL {
        if (urlValidator.isValid(url, null)) {
            throw new IllegalArgumentException(MessageFormat.format("URL isn't valid: {0}", url));
        }
    }

}
