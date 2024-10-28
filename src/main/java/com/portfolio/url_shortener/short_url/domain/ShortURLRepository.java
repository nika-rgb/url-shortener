package com.portfolio.url_shortener.short_url.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortURLRepository extends JpaRepository<ShortURL, ShortUrlId> {
    @Query("SELECT new com.portfolio.url_shortener.short_url.domain.URL(sh.originalUrl) FROM ShortURL sh WHERE sh.shortUrl = :shortUrl")
    Optional<URL> findByShortUrl(URL shortUrl);
}
