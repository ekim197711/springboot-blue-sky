package com.example.springbootbluesky.integration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginService {
    //    curl -X POST $PDSHOST/xrpc/com.atproto.server.createSession \
//            -H "Content-Type: application/json" \
//            -d '{"identifier": "'"$BLUESKY_HANDLE"'", "password": "'"$BLUESKY_PASSWORD"'"}'
    public static final String PDS_HOST = "https://bsky.social";
    private static final String URL_LOGIN = PDS_HOST + "/xrpc/com.atproto.server.createSession";

    public String login() {
        String identifier = System.getenv("BSKY_IDENTIFIER");
        String password = System.getenv("BSKY_PASSWORD");
        Map<String, String> dataToPost = Map.of("identifier", identifier, "password", password);
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity request = RequestEntity.post(URL_LOGIN)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(dataToPost);
        return Objects.requireNonNull(restTemplate.exchange(request, Map.class).getBody())
                .get("accessJwt").toString();
    }
}
