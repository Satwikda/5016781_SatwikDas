package com.example.bookstoreapi.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final Counter bookCreatedCounter;

    public BookService(MeterRegistry meterRegistry) {
        this.bookCreatedCounter = meterRegistry.counter("book.created.count");
    }

    public Book createBook(Book book) {
        bookCreatedCounter.increment();
        return book;
    }
}