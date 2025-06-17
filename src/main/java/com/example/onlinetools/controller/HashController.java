package com.example.onlinetools.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@RestController
@RequestMapping("/api/hash")
public class HashController {

    @PostMapping("/md5")
    public ResponseEntity<String> md5Hash(@RequestBody String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(text.getBytes());
        String hash = HexFormat.of().formatHex(digest);
        return ResponseEntity.ok(hash);
    }

    @PostMapping("/sha256")
    public ResponseEntity<String> sha256Hash(@RequestBody String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(text.getBytes());
        String hash = HexFormat.of().formatHex(digest);
        return ResponseEntity.ok(hash);
    }

    @PostMapping("/sha512")
    public ResponseEntity<String> sha512Hash(@RequestBody String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] digest = md.digest(text.getBytes());
        String hash = HexFormat.of().formatHex(digest);
        return ResponseEntity.ok(hash);
    }

    @PostMapping("/keccak256")
    public ResponseEntity<String> keccak256Hash(@RequestBody String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("Keccak-256");
        byte[] digest = md.digest(text.getBytes());
        String hash = HexFormat.of().formatHex(digest);
        return ResponseEntity.ok(hash);
    }
}
