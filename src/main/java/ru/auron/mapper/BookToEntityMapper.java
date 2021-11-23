package ru.auron.mapper;

import org.mapstruct.Mapper;
import ru.auron.dto.BookEntity;
import ru.auron.model.Book;

@Mapper(componentModel = "spring")
public interface BookToEntityMapper {

    BookEntity bookToBookEntity(Book book);
    Book bookEntityToBook(BookEntity bookEntity);

}
