package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.BookstoreApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = BookstoreApiApplication.class)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetBooks() throws Exception {
        mockMvc.perform(get("/books"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"));
    }
    
    @Test
    void testCreateBook() throws Exception {
        String newBook = "{\"title\":\"New Book\",\"author\":\"Author Name\",\"price\":19.99,\"isbn\":\"1234567890\"}";

        mockMvc.perform(post("/books")
                .contentType("application/json")
                .content(newBook))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.title").value("New Book"));
    }
}