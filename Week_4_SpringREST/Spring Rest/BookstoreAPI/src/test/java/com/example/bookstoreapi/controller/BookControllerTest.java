package com.example.bookstoreapi.controller;

import static org.junit.jupiter.api.Assertions.*;
import com.example.bookstoreapi.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void testGetBooks() throws Exception {
        mockMvc.perform(get("/books"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"));
    }
    
    @Test
    void testGetBookById() throws Exception {
        mockMvc.perform(get("/books/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.title").value("Sample Book"));
    }

    @Test
    void testCreateBook() throws Exception {
        String newBook = "{\"title\":\"New Book\",\"author\":\"Author Name\",\"price\":19.99,\"isbn\":\"1234567890\"}";

        mockMvc.perform(post("/books")
                .contentType("application/json")
                .content(newBook))
               .andExpect(status().isCreated());
    }
}