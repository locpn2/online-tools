package com.example.onlinetools.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.onlinetools.service.ExcelConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/excel")
public class ExcelConverterController {

    @Autowired
    private ExcelConverterService excelConverterService;

    @PostMapping("/convert")
    public String convertExcel(@RequestParam("file") MultipartFile file, @RequestParam("format") String format) {
        return excelConverterService.convertExcel(file, format);
    }
}
