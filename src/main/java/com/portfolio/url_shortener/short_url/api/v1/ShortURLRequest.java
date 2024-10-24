package com.portfolio.url_shortener.short_url.api.v1;


import org.hibernate.validator.constraints.URL;

public record ShortURLRequest(@URL String originalURL) {

}
