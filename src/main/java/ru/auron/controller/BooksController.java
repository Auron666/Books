package ru.auron.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.auron.mapper.BookToDtoMapper;
import ru.auron.model.Book;
import ru.auron.model.BookRequest;
import ru.auron.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;
    private final BookToDtoMapper mapper;

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping
    public List<Book> getAllBooks(@RequestParam(required = false) String author) {
        if (author != null)
            return bookService.findByAuthor(author);

        return bookService.getAllBooks();
    }

    @PostMapping
    public void addBook(@RequestBody BookRequest request) {
        bookService.addBook(mapper.addBookRequestToBook(request));
    }

    @PutMapping("/{id}")
    public void editBook(@PathVariable Long id, @RequestBody BookRequest request) {
        bookService.editBook(mapper.editBookRequestToBook(id, request));
    }

}
