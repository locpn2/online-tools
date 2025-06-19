package com.example.onlinetools.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExcelConverterService {

    public String convertExcel(MultipartFile file, String format) {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            List<Map<String, String>> data = readSheetData(sheet);

            switch (format.toLowerCase()) {
                case "json":
                    return convertToJson(data);
                case "xml":
                    return convertToXml(data);
                case "csv":
                    return convertToCsv(data);
                default:
                    return "Unsupported format: " + format;
            }
        } catch (IOException e) {
            return "Error converting Excel file: " + e.getMessage();
        }
    }

    private List<Map<String, String>> readSheetData(Sheet sheet) {
        List<Map<String, String>> data = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        List<String> headers = new ArrayList<>();
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            headers.add(headerRow.getCell(i).getStringCellValue());
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row dataRow = sheet.getRow(i);
            if (dataRow == null) {
                continue;
            }
            Map<String, String> rowData = new HashMap<>();
            for (int j = 0; j < headers.size(); j++) {
                Cell cell = dataRow.getCell(j);
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.put(headers.get(j), cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.put(headers.get(j), String.valueOf(cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            rowData.put(headers.get(j), String.valueOf(cell.getBooleanCellValue()));
                            break;
                        case FORMULA:
                            rowData.put(headers.get(j), cell.getCellFormula());
                            break;
                        default:
                            rowData.put(headers.get(j), "");
                    }
                } else {
                    rowData.put(headers.get(j), "");
                }
            }
            data.add(rowData);
        }
        return data;
    }

    private String convertToJson(List<Map<String, String>> data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            return "Error converting to JSON: " + e.getMessage();
        }
    }

    private String convertToXml(List<Map<String, String>> data) {
        try {
            StringBuilder xml = new StringBuilder();
            xml.append("<data>\n");
            for (Map<String, String> row : data) {
                xml.append("  <row>\n");
                for (Map.Entry<String, String> entry : row.entrySet()) {
                    xml.append("    <").append(entry.getKey()).append(">");
                    xml.append(entry.getValue());
                    xml.append("</").append(entry.getKey()).append(">\n");
                }
                xml.append("  </row>\n");
            }
            xml.append("</data>\n");
            return xml.toString();
        } catch (Exception e) {
            return "Error converting to XML: " + e.getMessage();
        }
    }

    private String convertToCsv(List<Map<String, String>> data) {
        try {
            StringBuilder csv = new StringBuilder();
            if (data != null && !data.isEmpty()) {
                // Append headers
                List<String> headers = new ArrayList<>(data.get(0).keySet());
                csv.append(String.join(",", headers)).append("\n");

                // Append data rows
                for (Map<String, String> row : data) {
                    List<String> values = new ArrayList<>();
                    for (String header : headers) {
                        values.add(row.getOrDefault(header, ""));
                    }
                    csv.append(String.join(",", values)).append("\n");
                }
            }
            return csv.toString();
        } catch (Exception e) {
            return "Error converting to CSV: " + e.getMessage();
        }
    }
}
