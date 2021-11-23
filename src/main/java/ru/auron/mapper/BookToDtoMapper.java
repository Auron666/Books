package ru.auron.mapper;

import org.mapstruct.Mapper;
import ru.auron.model.Book;
import ru.auron.model.BookRequest;

@Mapper(componentModel = "spring")
public interface BookToDtoMapper {

    Book addBookRequestToBook(BookRequest bookRequest);
    Book editBookRequestToBook(Long id, BookRequest bookRequest);
}
