package com.example.onlinetools.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.http.MediaType;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

@SpringBootTest
@AutoConfigureMockMvc
public class FormatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFormat() throws Exception {
        String expectedJson = "{\"key\":\"value\"}";

        String result = mockMvc.perform(MockMvcRequestBuilders.post("/api/format/json")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.LENIENT);
    }
}
