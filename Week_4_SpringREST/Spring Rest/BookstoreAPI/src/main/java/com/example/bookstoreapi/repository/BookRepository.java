package com.example.bookstoreapi.repository;

import com.example.bookstoreapi.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}