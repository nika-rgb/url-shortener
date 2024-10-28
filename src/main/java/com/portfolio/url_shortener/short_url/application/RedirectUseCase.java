package com.portfolio.url_shortener.short_url.application;

import com.portfolio.url_shortener.UseCase;
import com.portfolio.url_shortener.config.URLShortenerConfiguration;
import com.portfolio.url_shortener.short_url.domain.ShortURLRepository;
import com.portfolio.url_shortener.short_url.domain.URL;

@UseCase
public class RedirectUseCase {
    private final ShortURLRepository shortURLRepository;
    private final URLShortenerConfiguration configuration;

    public RedirectUseCase(ShortURLRepository shortURLRepository, URLShortenerConfiguration configuration) {
        this.shortURLRepository = shortURLRepository;
        this.configuration = configuration;
    }


    public URL getOriginalURLByShortURL(String path) {
        URL shortURL = new URL(configuration.getBaseUrl(), path);
        return shortURLRepository.findByShortUrl(shortURL)
                .orElseThrow(() -> new URLNotFound("Requested URL is not found"));
    }

}
