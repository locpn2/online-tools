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
public class EncodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testEncode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/encode")
                .param("text", "test"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
