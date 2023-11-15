package com.mitiro.database.dao;

import com.mitiro.database.domain.Author;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

   Optional<Author> findOne(long l);

    List<Author> findMany();

    void update(Long authorId, Author author);

    void delete(long l);
}
