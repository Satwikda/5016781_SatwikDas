package com.example.bookstoreapi.dto;

import org.springframework.hateoas.RepresentationModel;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BookDTO extends RepresentationModel<BookDTO> {
    private int id;

    @NotNull
    @Size(min = 2, max = 100)
    private String title;

    @NotNull
    @Size(min = 2, max = 50)
    private String author;

    @Min(0)
    private double price;

    @NotNull
    @Size(min = 10, max = 13)
    private String isbn;
}
