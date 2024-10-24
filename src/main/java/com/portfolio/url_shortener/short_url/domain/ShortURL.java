package com.portfolio.url_shortener.short_url.domain;

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
    @AttributeOverride(name = "shortUrl", column = @Column(name = "shortUrl", nullable = false, updatable = false, length = 10))
    private URL shortUrl;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

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
