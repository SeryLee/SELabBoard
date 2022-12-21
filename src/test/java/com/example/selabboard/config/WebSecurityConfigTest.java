package com.example.selabboard.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WebSecurityConfigTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void getPasswordEncoder() {
        String password = "1234";

        String encodedPassword = passwordEncoder.encode(password);

        assertThat(!password.equals(encodedPassword));
        assertThat(passwordEncoder.matches(password, encodedPassword));
    }

}