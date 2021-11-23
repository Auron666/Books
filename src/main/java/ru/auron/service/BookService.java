package ru.auron.service;

import ru.auron.model.Book;

import java.util.List;

public interface BookService {

    Book getBookById(Long id);
    List<Book> getAllBooks();
    void addBook(Book book);
    List<Book> findByAuthor(String author);
    void editBook(Book book);

}
