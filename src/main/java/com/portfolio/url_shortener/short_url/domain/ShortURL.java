package com.portfolio.url_shortener.short_url.domain;

import com.portfolio.url_shortener.config.URLShortenerConfiguration;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "SHORT_URL")
public class ShortURL {

    @EmbeddedId
    private ShortUrlId id;

    @Embedded
    @AttributeOverride(name = "url", column = @Column(name = "originalUrl", nullable = false, updatable = false, length = 255))
    private URL originalUrl;

    @Embedded
    @AttributeOverride(name = "url", column = @Column(name = "shortUrl", nullable = false, updatable = false, length = 30))
    private URL shortUrl;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    // Needed for JPA/Hibernate
    ShortURL() {}

    public ShortURL(URL originalUrl, URL shortUrl, URLShortenerConfiguration configuration) {

        if (!configuration.getGeneratedUrlLength().equals(getPath(shortUrl).length() - 1)) {
            throw new IllegalArgumentException("Invalid short URL generated");
        }

        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
        this.createdAt = LocalDateTime.now();
    }

    private String getPath(URL shortUrl) {
        try {
            return new java.net.URL(shortUrl.url()).getPath();
        } catch (Exception ignore){
            return "";
        }
    }

    public ShortUrlId getId() {
        return id;
    }

    public void setId(ShortUrlId id) {
        this.id = id;
    }

    public URL getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(URL originalUrl) {
        this.originalUrl = originalUrl;
    }

    public URL getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(URL shortUrl) {
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
