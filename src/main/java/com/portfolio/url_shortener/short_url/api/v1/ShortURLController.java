package com.portfolio.url_shortener.short_url.api.v1;

import com.portfolio.url_shortener.short_url.application.RedirectUseCase;
import com.portfolio.url_shortener.short_url.application.ShortenUrlUseCase;
import com.portfolio.url_shortener.short_url.domain.ShortURLProjection;
import com.portfolio.url_shortener.short_url.domain.URL;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ShortURLController {

    private final ShortenUrlUseCase shortenUrlUseCase;
    private final RedirectUseCase redirectUseCase;

    public ShortURLController(ShortenUrlUseCase shortenUrlUseCase, RedirectUseCase redirectUseCase) {
        this.shortenUrlUseCase = shortenUrlUseCase;
        this.redirectUseCase = redirectUseCase;
    }


    @PostMapping("/generate")
    public ResponseEntity<ShortURLProjection> generateShortURL(@Valid @RequestBody ShortURLRequest request) {
        return ResponseEntity.ok(shortenUrlUseCase.generateShortUrl(request.originalURL()));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(HttpServletRequest request) {
        URL originalURL = redirectUseCase.getOriginalURLByShortURL(request.getPathInfo());
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, originalURL.url())
                .body(null);
    }

}
