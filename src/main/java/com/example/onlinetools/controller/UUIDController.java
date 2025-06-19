package com.example.onlinetools.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController
@RequestMapping("/uuid")
public class UUIDController {

    @GetMapping("/v4")
    public ResponseEntity<String> generateUUIDv4() {
        UUID uuid = UUID.randomUUID();
        return ResponseEntity.ok(uuid.toString());
    }

    @GetMapping("/v5")
    public ResponseEntity<String> generateUUIDv5(@RequestParam String name) {
        String namespace = "example.com"; // You can change this to a configurable value
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = md.digest((namespace + name).getBytes(StandardCharsets.UTF_8));
            byte[] uuidBytes = new byte[16];
            System.arraycopy(hashBytes, 0, uuidBytes, 0, 16);

            // Set the version (bits 48-51) to 5
            uuidBytes[6] = (byte) ((uuidBytes[6] & 0x0f) | 0x50);

            // Set the variant (bits 62-63) to 10
            uuidBytes[8] = (byte) ((uuidBytes[8] & 0x3f) | 0x80);

            long msb = 0;
            long lsb = 0;

            for (int i = 0; i < 8; i++) {
                msb = (msb << 8) | (uuidBytes[i] & 0xff);
            }

            for (int i = 8; i < 16; i++) {
                lsb = (lsb << 8) | (uuidBytes[i] & 0xff);
            }

            UUID uuid = new UUID(msb, lsb);
            return ResponseEntity.ok(uuid.toString());

        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.internalServerError().body("{\"error\": \"Error generating UUIDv5: Algorithm not found\"}");
        }
    }
}
