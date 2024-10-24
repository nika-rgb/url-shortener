package com.portfolio.url_shortener.short_url.domain;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Optional;

public record ShortUrlId(Long id) implements Serializable {

   public ShortUrlId {
       Optional.ofNullable(id)
               .orElseThrow(() -> new InvalidParameterException("Id can't be nullable"));
   }

}
