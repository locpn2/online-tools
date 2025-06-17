package com.example.onlinetools.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api")
public class EncodeController {

    @PostMapping("/encode/base64")
    public ResponseEntity<String> base64Encode(@RequestBody String text) {
        String encodedString = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
        return ResponseEntity.ok(encodedString);
    }

    @PostMapping("/decode/base64")
    public ResponseEntity<String> base64Decode(@RequestBody String base64String) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);
        return ResponseEntity.ok(decodedString);
    }

    @PostMapping("/encode/url")
    public ResponseEntity<String> urlEncode(@RequestBody String text) throws Exception {
        String encodedString = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
        return ResponseEntity.ok(encodedString);
    }

    @PostMapping("/decode/url")
    public ResponseEntity<String> urlDecode(@RequestBody String encodedString) throws Exception {
        String decodedString = URLDecoder.decode(encodedString, StandardCharsets.UTF_8.toString());
        return ResponseEntity.ok(decodedString);
    }
}
