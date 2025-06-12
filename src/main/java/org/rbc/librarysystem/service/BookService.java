package org.rbc.librarysystem.service;

import org.rbc.librarysystem.exception.BookNotFoundException;
import org.rbc.librarysystem.model.Book;
import org.rbc.librarysystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repo;

    public Book addBook(Book book) {
        return repo.save(book);
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public Book getBookById(Long id) {
        return repo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existing = repo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        existing.setTitle(updatedBook.getTitle());
        existing.setAuthor(updatedBook.getAuthor());
        existing.setPrice(updatedBook.getPrice());
        return repo.save(existing);
    }

    public void deleteBook(Long id) {
        Book book = repo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        repo.delete(book);
    }
}

