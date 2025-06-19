package com.example.onlinetools.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UUIDControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void generateUUIDv4Test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/uuid/v4"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(matchesPattern(
                        "[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}"
                )));
    }

    @Test
    public void generateUUIDv5Test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/uuid/v5?name=test"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(matchesPattern(
                        "[0-9a-f]{8}-[0-9a-f]{4}-5[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}"
                )));
    }
}
