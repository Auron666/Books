package ru.auron.repository;

import org.springframework.data.repository.CrudRepository;
import ru.auron.dto.BookEntity;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
    List<BookEntity> findAllByAuthorContaining(String author);
}
