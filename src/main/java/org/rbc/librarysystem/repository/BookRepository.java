package org.rbc.librarysystem.repository;

import org.rbc.librarysystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
