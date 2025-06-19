package com.example.onlinetools.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class GenerationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGenerateUuid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/generate/uuid"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.matchesPattern("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")));
    }

    @Test
    public void testGenerateQrCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/generate/qrcode").param("text", "test"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGenerateUUIDv4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/generate/uuid/v4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.matchesPattern("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")));
    }

    @Test
    public void testGenerateUUIDv5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/generate/uuid/v5").param("name", "test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.matchesPattern("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")));
    }
}
