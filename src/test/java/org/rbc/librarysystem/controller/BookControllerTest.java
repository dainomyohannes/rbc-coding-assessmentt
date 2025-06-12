package org.rbc.librarysystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rbc.librarysystem.model.Book;
import org.rbc.librarysystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    // ✅ POST /books
    @Test
    void shouldCreateBook() throws Exception {
        Book book = new Book(1L, "Test Title", "Test Author", 10.0);

        Mockito.when(bookService.addBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.price").value(10.0));
    }

    // ✅ GET /books/{id}
    @Test
    void shouldReturnBookById() throws Exception {
        Book book = new Book(1L, "Test Title", "Test Author", 10.0);

        Mockito.when(bookService.getBookById(1L)).thenReturn(book);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.price").value(10.0));
    }
}