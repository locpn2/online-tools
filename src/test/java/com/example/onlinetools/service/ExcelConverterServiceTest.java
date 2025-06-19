package com.example.onlinetools.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ExcelConverterServiceTest {

    @Autowired
    private ExcelConverterService excelConverterService;

    @Test
    public void testConvertToXml() throws IOException {
        // Load the test Excel file
        Path path = Paths.get("src/test/resources/test.xlsx");
        String name = "test.xlsx";
        String originalFileName = "test.xlsx";
        String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        byte[] content = Files.readAllBytes(path);

        MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);

        // Convert the Excel file to XML
        String xmlResult = excelConverterService.convertExcel(file, "xml");

        // Assert that the XML result is not empty and contains the expected data
        assertTrue(xmlResult.contains("<data>"));
        assertTrue(xmlResult.contains("<row>"));
        assertTrue(xmlResult.contains("<Name>John</Name>"));
        assertTrue(xmlResult.contains("<Age>30.0</Age>"));
        assertTrue(xmlResult.contains("<City>New York</City>"));
        assertTrue(xmlResult.contains("</row>"));
        assertTrue(xmlResult.contains("</data>"));
    }

    @Test
    public void testConvertToJson() throws IOException {
        // Load the test Excel file
        Path path = Paths.get("src/test/resources/test.xlsx");
        String name = "test.xlsx";
        String originalFileName = "test.xlsx";
        String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        byte[] content = Files.readAllBytes(path);

        MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);

        // Convert the Excel file to JSON
        String jsonResult = excelConverterService.convertExcel(file, "json");

        // Print the JSON result to the console
        System.out.println("JSON Result: " + jsonResult);

        // Assert that the JSON result is not empty and contains the expected data
        assertTrue(jsonResult.contains("\"City\":\"New York\""));
        assertTrue(jsonResult.contains("\"Age\":\"30.0\""));
        assertTrue(jsonResult.contains("\"Name\":\"John\""));
    }

    @Test
    public void testConvertToCsv() throws IOException {
        // Load the test Excel file
        Path path = Paths.get("src/test/resources/test.xlsx");
        String name = "test.xlsx";
        String originalFileName = "test.xlsx";
        String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        byte[] content = Files.readAllBytes(path);

        MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);

        // Convert the Excel file to CSV
        String csvResult = excelConverterService.convertExcel(file, "csv");

        // Print the CSV result to the console
        System.out.println("CSV Result: " + csvResult);

        // Assert that the CSV result is not empty and contains the expected data
        assertEquals("City,Age,Name\nNew York,30.0,John\n", csvResult);
    }
}
