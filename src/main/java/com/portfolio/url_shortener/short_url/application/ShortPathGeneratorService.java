package com.portfolio.url_shortener.short_url.application;

public interface ShortPathGeneratorService {
     String generateUniquePath(String originalUrl, int pathSize);
}
