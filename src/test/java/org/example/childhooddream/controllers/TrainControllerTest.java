package org.example.childhooddream.controllers;

import org.example.childhooddream.entities.Train;
import org.example.childhooddream.repositories.TrainRepository;
import org.example.childhooddream.services.TrainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class TrainControllerTest {
    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private MockMvc mvc;


    @Test
    void testGetTrains() throws Exception {
        mvc.perform(get("/trains")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(5)));
    }

    @Test
    void testGetTrain_ValidId() throws Exception {
        mvc.perform(get("/trains/5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Star Wars")));
    }

    @Test
    void testGetTrain_InvalidId() throws Exception {
        mvc.perform(get("/trains/9")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void handleInvalidEndpoints() throws Exception {
        mvc.perform(get("/trainssss")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(jsonPath("$.message",is("invalid endpoint")));

    }
}