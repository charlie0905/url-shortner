package com.charlie.urlshortner.controller;

import com.charlie.urlshortner.service.UrlShortenerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UrlShortenerController {

    private final UrlShortenerService service;

    @Operation(
            summary = "Shorten a long URL",
            description = "Returns a short code for the provided URL"
    )
    @PostMapping("/shorten")
    public String shorten(@RequestBody String longUrl) {
        return service.shortenUrl(longUrl);
    }

    @Operation(
            summary = "Redirect to original URL",
            description = "Takes a short code and redirects to the full URL"
    )
    @GetMapping("/{shortCode}")
    public void redirect(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        String originalUrl = service.getOriginalUrl(shortCode);

        if (originalUrl == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Short URL not found");
            return;
        }

        response.sendRedirect(originalUrl);
    }
}
