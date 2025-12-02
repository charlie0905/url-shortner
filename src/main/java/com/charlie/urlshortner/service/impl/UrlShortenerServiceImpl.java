package com.charlie.urlshortner.service.impl;

import com.charlie.urlshortner.entity.UrlMapping;
import com.charlie.urlshortner.repository.UrlRepository;
import com.charlie.urlshortner.service.UrlShortenerService;
import com.charlie.urlshortner.util.Base62Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private final UrlRepository urlRepository;

    @Override
    public String shortenUrl(String originalUrl) {
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);

        // Save first to generate ID
        UrlMapping saved = urlRepository.save(mapping);

        // Encode ID → Base62 → short code
        String shortCode = Base62Encoder.encode(saved.getId(),6);
        saved.setShortCode(shortCode);

        urlRepository.save(saved);
        return shortCode;
    }

    @Override
    public String getOriginalUrl(String shortCode) {
        UrlMapping mapping = urlRepository.findByShortCode(shortCode);
        if (mapping == null) return null;
        return mapping.getOriginalUrl();
    }
}
