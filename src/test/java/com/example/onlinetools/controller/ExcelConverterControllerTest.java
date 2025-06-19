package com.example.onlinetools.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExcelConverterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testConvertExcel() throws Exception {
        // Load the test Excel file
        Path path = Paths.get("src/test/resources/test.xlsx");
        String name = "test.xlsx";
        String originalFileName = "test.xlsx";
        String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        byte[] content = Files.readAllBytes(path);

        MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/excel/convert")
                .file("file", file.getBytes())
                .param("format", "json"))
                .andExpect(status().isOk());
    }
}
