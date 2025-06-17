package com.example.onlinetools.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HashControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testSha256Hash() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/hash/sha256")
                .contentType(MediaType.TEXT_PLAIN)
                .content("test"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08"));
    }

    @Test
    public void testSha512Hash() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/hash/sha512")
                .contentType(MediaType.TEXT_PLAIN)
                .content("test"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff"));
    }

    @Test
    public void testKeccak256Hash() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/hash/keccak256")
                .contentType(MediaType.TEXT_PLAIN)
                .content("test"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("36f028580bb02cc8272a9a020f4200e346e276ae664e45ee80745574e2f5ab80"));
    }
}
