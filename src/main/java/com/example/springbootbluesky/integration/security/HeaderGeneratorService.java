package com.example.springbootbluesky.integration.security;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class HeaderGeneratorService {
    public HttpHeaders generate(String token) {
        HttpHeaders headers = new HttpHeaders();
        String bearer = "Bearer ";
        headers.add(HttpHeaders.AUTHORIZATION, bearer + token);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return headers;
    }
}
