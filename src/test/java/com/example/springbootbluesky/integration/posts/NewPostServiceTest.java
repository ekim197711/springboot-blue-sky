package com.example.springbootbluesky.integration.posts;

import com.example.springbootbluesky.integration.security.HeaderGeneratorService;
import com.example.springbootbluesky.integration.security.LoginService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class NewPostServiceTest {

    @Test
    void createPost() {
        String response = new NewPostService(
                new HeaderGeneratorService(),
                new LoginService()
        ).createPost(String.format("""
                Another message! Eat vegestables!
                Timestamp: %s
                """, LocalDateTime.now()));
        System.out.println(response);
        Assertions.assertThat(response).isNotBlank();
    }
}