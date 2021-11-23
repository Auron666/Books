package ru.auron.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import ru.auron.dto.BookEntity;
import ru.auron.exception.BookNotFoundException;
import ru.auron.mapper.BookToEntityMapper;
import ru.auron.model.Book;
import ru.auron.repository.BookRepository;
import ru.auron.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookToEntityMapper mapper;

    @Override
    public Book getBookById(Long id) {
        BookEntity bookEntity = bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found: id = " + id));

        return mapper.bookEntityToBook(bookEntity);
    }

    @Override
    public List<Book> getAllBooks() {
        Iterable<BookEntity> iterable = bookRepository.findAll();
        ArrayList<Book> books = new ArrayList<>();
        for (BookEntity bookEntity : iterable) {
            books.add(mapper.bookEntityToBook(bookEntity));
        }

        return books;
    }

    @Override
    public void addBook(Book book) {
        BookEntity bookEntity = mapper.bookToBookEntity(book);
        bookRepository.save(bookEntity);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        Iterable<BookEntity> iterable = bookRepository.findAllByAuthorContaining(author);
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::bookEntityToBook)
                .collect(Collectors.toList());
    }

    @Override
    public void editBook(Book book) {
        if (!bookRepository.existsById(book.getId()))
            throw new BookNotFoundException("Book not found: id = " + book.getId());

        BookEntity bookEntity = mapper.bookToBookEntity(book);
        bookRepository.save(bookEntity);
    }
}
