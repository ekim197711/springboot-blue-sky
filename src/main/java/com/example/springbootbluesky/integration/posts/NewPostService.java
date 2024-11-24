package com.example.springbootbluesky.integration.posts;

import com.example.springbootbluesky.integration.security.HeaderGeneratorService;
import com.example.springbootbluesky.integration.security.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewPostService {
    private final HeaderGeneratorService headerGeneratorService;
    private final LoginService loginService;

    private final String URL = LoginService.PDS_HOST + "/xrpc/com.atproto.repo.createRecord";
//    curl -X POST  \
//            -H "Authorization: Bearer $ACCESS_JWT" \
//            -H "Content-Type: application/json" \
//            -d "{\"repo\": \"$BLUESKY_HANDLE\", \"collection\": \"app.bsky.feed.post\", \"record\": {\"text\": \"Hello world! I posted this via the API.\", \"createdAt\": \"$(date -u +%Y-%m-%dT%H:%M:%SZ)\"}}"

    public String createPost(String content) {
        String token = loginService.login();
        HttpHeaders headers = headerGeneratorService.generate(token);
        String bskyHandle = System.getenv("BSKY_IDENTIFIER");
        Map data = Map.of(
                "repo", bskyHandle,
                "collection", "app.bsky.feed.post",
                "record", Map.of(
                        "text", content,
                        "createdAt",
                        ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                )
        );
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                RequestEntity.post(URL)
                        .headers(headers)
                        .body(data)
                , String.class).getBody();
    }
}
