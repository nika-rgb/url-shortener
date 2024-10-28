package com.portfolio.url_shortener.short_url.application;

import com.portfolio.url_shortener.UseCase;
import com.portfolio.url_shortener.config.URLShortenerConfiguration;
import com.portfolio.url_shortener.short_url.domain.ShortURL;
import com.portfolio.url_shortener.short_url.domain.ShortURLProjection;
import com.portfolio.url_shortener.short_url.domain.ShortURLRepository;
import com.portfolio.url_shortener.short_url.domain.URL;

@UseCase
public class ShortenUrlUseCase {
    private final ShortPathGeneratorService urlPathGeneratorService;
    private final ShortURLRepository shortURLRepository;
    private final URLShortenerConfiguration configuration;

    public ShortenUrlUseCase(ShortPathGeneratorService urlPathGeneratorService, ShortURLRepository shortURLRepository, URLShortenerConfiguration configuration) {
        this.urlPathGeneratorService = urlPathGeneratorService;
        this.shortURLRepository = shortURLRepository;
        this.configuration = configuration;
    }

    public ShortURLProjection generateShortUrl(String originalUrl) {
        ShortURL shortURL = new ShortURL(
                new URL(originalUrl),
                new URL(configuration.getBaseUrl(), configuration.getRedirectBasePath(),
                        urlPathGeneratorService.generateUniquePath(originalUrl, configuration.getGeneratedUrlLength())),
                configuration
        );
        shortURL = shortURLRepository.save(shortURL);
        return new ShortURLProjection(shortURL.getShortUrl(), shortURL.getOriginalUrl(), shortURL.getCreatedAt());
    }

}
