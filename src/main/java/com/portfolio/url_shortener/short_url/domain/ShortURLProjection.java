package com.portfolio.url_shortener.short_url.domain;

import java.time.LocalDateTime;

public record ShortURLProjection(URL shortUrl, URL originalUrl, LocalDateTime createdAt) {
}
