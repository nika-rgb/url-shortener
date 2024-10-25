package com.portfolio.url_shortener.short_url.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Optional;

@SequenceGenerator(
        name = "short-url-sequence",
        sequenceName = "short-url-sequence",
        allocationSize = 1
)
public record ShortUrlId(@GeneratedValue(generator = "short-url-sequence") Long id) implements Serializable {

   public ShortUrlId {
       Optional.ofNullable(id)
               .orElseThrow(() -> new InvalidParameterException("Id can't be nullable"));
   }

}
