package com.example.onlinetools.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/generate")
public class GenerationController {

    private static final UUID NAMESPACE_FOR_APP = UUID.fromString("a7a729d0-b234-4913-868c-054516c3c101");

    @GetMapping("/uuid")
    public ResponseEntity<String> generateUuid() {
        UUID uuid = UUID.randomUUID();
        return ResponseEntity.ok(uuid.toString());
    }

    @GetMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQrCode(@RequestParam("text") String text, @RequestParam(value = "format", required = false) String format) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 256, 256);

        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        byte[] qrCodeBytes = byteArrayOutputStream.toByteArray();

        if ("base64".equalsIgnoreCase(format)) {
            String base64Image = Base64.getEncoder().encodeToString(qrCodeBytes);
            return ResponseEntity.ok(base64Image.getBytes());
        } else {
            return ResponseEntity.ok(qrCodeBytes);
        }
    }

    @GetMapping("/uuid/v4")
    public ResponseEntity<String> generateUUIDv4() {
        UUID uuid = UUID.randomUUID();
        return ResponseEntity.ok(uuid.toString());
    }

    @GetMapping("/uuid/v5")
    public ResponseEntity<String> generateUUIDv5(@RequestParam("name") String name) {
        try {
            ByteBuffer namespaceBuffer = ByteBuffer.allocate(16);
            namespaceBuffer.putLong(NAMESPACE_FOR_APP.getMostSignificantBits());
            namespaceBuffer.putLong(NAMESPACE_FOR_APP.getLeastSignificantBits());
            byte[] namespaceBytes = namespaceBuffer.array();

            byte[] nameBytes = name.getBytes(StandardCharsets.UTF_8);

            ByteBuffer combinedBuffer = ByteBuffer.allocate(namespaceBytes.length + nameBytes.length);
            combinedBuffer.put(namespaceBytes);
            combinedBuffer.put(nameBytes);
            byte[] combinedInput = combinedBuffer.array();

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = md.digest(combinedInput);

            ByteBuffer buffer = ByteBuffer.wrap(hashBytes);
            long msb = buffer.getLong();
            long lsb = buffer.getLong();

            msb &= ~(0xF000L);
            msb |= (0x5000L);

            lsb &= ~(0xC000000000000000L);
            lsb |= (0x8000000000000000L);

            UUID uuid = new UUID(msb, lsb);
            return ResponseEntity.ok(uuid.toString());

        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.internalServerError().body("Error generating UUIDv5: Algorithm not found");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred during UUIDv5 generation");
        }
    }
}
