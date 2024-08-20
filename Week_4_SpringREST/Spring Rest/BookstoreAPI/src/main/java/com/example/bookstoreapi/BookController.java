package com.example.bookstoreapi;

import org.springframework.http.HttpHeaders;
import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.dto.BookMapper;
import com.example.bookstoreapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {
	private List<Book> books = new ArrayList<>();
	
	@Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }
    
    @Operation(summary = "Get all books")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the books",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = BookDTO.class)) }),
        @ApiResponse(responseCode = "404", description = "Books not found",
            content = @Content)
    })
    @GetMapping
    public List<BookDTO> getBooks() {
    }

    @Operation(summary = "Get a book by ID")
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") int id, @RequestBody Book book) {
        for (Book b : books) {
            if (b.getId() == id) {
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
                b.setPrice(book.getPrice());
                b.setIsbn(book.getIsbn());
                return b;
            }
        }
        return null; 
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable("id") int id) {
        return books.stream()
                    .filter(book -> book.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new BookNotFoundException(id));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id) {
        Book book = bookRepository.findById(id)
                                  .orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.delete(book);
    }
    
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(value = "title", required = false) String title,
                                  @RequestParam(value = "author", required = false) String author) {
        return books.stream()
                    .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                                    (author == null || book.getAuthor().equalsIgnoreCase(author)))
                    .toList();
    }
    
    @PostMapping("/addWithHeader")
    public ResponseEntity<Book> addBookWithHeader(@RequestBody Book book) {
        books.add(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookCreated");
        return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/withHeader")
    public ResponseEntity<Book> getBookByIdWithHeader(@PathVariable("id") int id) {
        Book book = books.stream()
                         .filter(b -> b.getId() == id)
                         .findFirst()
                         .orElseThrow(() -> new BookNotFoundException(id));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookFound");
        return new ResponseEntity<>(book, headers, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        BookDTO bookDTO = BookMapper.INSTANCE.bookToBookDTO(book);
        bookDTO.add(linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel());
        bookDTO.add(linkTo(methodOn(BookController.class).getAllBooks()).withRel("all-books"));
        return bookDTO;
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                             .map(book -> {
                                 BookDTO bookDTO = BookMapper.INSTANCE.bookToBookDTO(book);
                                 bookDTO.add(linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel());
                                 return bookDTO;
                             })
                             .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.bookDTOToBook(bookDTO);
        book = bookRepository.save(book);
        return BookMapper.INSTANCE.bookToBookDTO(book);
    }
    
    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable int id, @Valid @RequestBody BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setIsbn(bookDTO.getIsbn());
        book = bookRepository.save(book);
        return BookMapper.INSTANCE.bookToBookDTO(book);
    }
}
