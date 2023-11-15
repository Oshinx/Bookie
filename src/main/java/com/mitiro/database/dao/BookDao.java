package com.mitiro.database.dao;

import com.mitiro.database.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String s);

    List<Book> findMany();

    void update(String isbn, Book book);

    void delete(String isbn);
}
