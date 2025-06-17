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
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/api/generate")
public class GenerationController {

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
}
