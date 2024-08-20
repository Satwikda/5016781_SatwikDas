package com.example.bookstoreapi;

import lombok.Data;
import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String author;
    private double price;
    private String isbn;

    @Version
    private int version;
}