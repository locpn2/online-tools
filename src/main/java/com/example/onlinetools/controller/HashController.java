package com.example.onlinetools.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hash")
public class HashController {

    @PostMapping("/md5")
    public ResponseEntity<String> md5Hash(@RequestBody String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(text.getBytes());
        String hash = String.format("%032x", new java.math.BigInteger(1, digest));
        return ResponseEntity.ok(hash);
    }

    @PostMapping("/sha256")
    public ResponseEntity<String> sha256Hash(@RequestBody String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(text.getBytes());
        String hash = String.format("%032x", new java.math.BigInteger(1, digest));
        return ResponseEntity.ok(hash);
    }

    @PostMapping("/sha512")
    public ResponseEntity<String> sha512Hash(@RequestBody String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] digest = md.digest(text.getBytes());
        String hash = String.format("%0128x", new java.math.BigInteger(1, digest));
        return ResponseEntity.ok(hash);
    }

    @PostMapping("/keccak256")
    public ResponseEntity<String> keccak256Hash(@RequestBody String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA3-256");
        byte[] digest = md.digest(text.getBytes());
        String hash = String.format("%032x", new java.math.BigInteger(1, digest));
        return ResponseEntity.ok(hash);
    }
}
