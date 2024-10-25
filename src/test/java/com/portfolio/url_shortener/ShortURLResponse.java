package com.portfolio.url_shortener;

import java.time.LocalDateTime;

// Creating a separate class for the response to exactly mimic the production behaviour
public record ShortURLResponse(String originalUrl, String shortUrl, LocalDateTime createdAt) {


}
