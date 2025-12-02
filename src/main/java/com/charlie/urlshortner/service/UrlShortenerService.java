package com.charlie.urlshortner.service;

public interface UrlShortenerService {
    public String shortenUrl(String originalUrl);

    public String getOriginalUrl(String shortCode);
}
