package com.portfolio.url_shortener.short_url.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortURLRepository extends JpaRepository<ShortURL, ShortUrlId> {

}
