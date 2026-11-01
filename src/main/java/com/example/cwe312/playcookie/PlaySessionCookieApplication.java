package com.example.cwe312.playcookie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class PlaySessionCookieApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlaySessionCookieApplication.class, args);
    }

    // Remembers the user by storing the plaintext password in a cookie
    @PostMapping("/remember")
    public ResponseEntity<?> remember(@RequestBody Map<String, String> body) {
        String password = body.getOrDefault("password", "");
        ResponseCookie cookie = ResponseCookie.from("REMEMBER_ME", password)
                .path("/")
                .maxAge(3600)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("ok", true));
    }
}
