package com.example.springbootbluesky.integration.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LoginServiceTest {
    @Test
    void login() {
        LoginService loginService = new LoginService();
        String response = loginService.login();
        Assertions.assertThat(response).isNotNull();
    }
}