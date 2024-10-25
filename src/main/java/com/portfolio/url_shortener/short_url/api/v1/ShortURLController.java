package com.portfolio.url_shortener.short_url.api.v1;

import com.portfolio.url_shortener.short_url.application.ShortenUrlUseCase;
import com.portfolio.url_shortener.short_url.domain.ShortURLProjection;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/url-shortener")
public class ShortURLController {
    private final ShortenUrlUseCase useCase;

    public ShortURLController(ShortenUrlUseCase useCase) {
        this.useCase = useCase;
    }


    @PostMapping("/generate")
    public ResponseEntity<ShortURLProjection> generateShortURL(@Valid @RequestBody ShortURLRequest request) {
        return ResponseEntity.ok(useCase.generateShortUrl(request.originalURL()));
    }

}
