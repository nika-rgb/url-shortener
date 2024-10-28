package com.portfolio.url_shortener.short_url.domain;

import com.portfolio.url_shortener.config.URLShortenerConfiguration;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "SHORT_URL")
public class ShortURL {

    public static final String URL_DELIMETER = "/";
    @EmbeddedId
    private ShortUrlId id;

    @Embedded
    @AttributeOverride(name = "url", column = @Column(name = "originalUrl", nullable = false, updatable = false, length = 255))
    private URL originalUrl;

    @Embedded
    @AttributeOverride(name = "url", column = @Column(name = "shortUrl", nullable = false, updatable = false, length = 50))
    private URL shortUrl;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    // Needed for JPA/Hibernate
    ShortURL() {}

    public ShortURL(URL originalUrl, URL shortUrl, URLShortenerConfiguration configuration) {

        if (!configuration.getGeneratedUrlLength().equals(getGeneratedPath(shortUrl).length() - 1)) {
            throw new IllegalArgumentException("Invalid short URL generated");
        }

        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
        this.createdAt = LocalDateTime.now();
    }

    private String getGeneratedPath(URL shortUrl) {
        try {
            String fullPath = new java.net.URL(shortUrl.url()).getPath();
            // Return only the generated part of full path
            return fullPath.substring(fullPath.lastIndexOf(URL_DELIMETER));
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
