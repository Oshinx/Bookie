package com.mitiro.database.dao.impl;


import com.mitiro.database.TestDataUtil;
import com.mitiro.database.dao.AuthorDao;
import com.mitiro.database.domain.Author;
import com.mitiro.database.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImpIntergrationTests {

    private BookDaoImpl bookDaoImpl;

    private AuthorDao authorDao;
    private BookDaoImpl.BookRowMapper bookRowMapper;

    @Autowired
    public BookDaoImpIntergrationTests(BookDaoImpl bookDaoImpl, BookDaoImpl.BookRowMapper bookRowMapper, AuthorDao authorDao) {
        this.bookDaoImpl = bookDaoImpl;
        this.bookRowMapper = bookRowMapper;
        this.authorDao = authorDao;
    }


    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);


        Book book = TestDataUtil.createTestBook();
        bookDaoImpl.create(book);
        Optional<Book> result = bookDaoImpl.findOne(book.getIsbn());

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedandRecalled() {
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);

        Book booka = TestDataUtil.createTestBooka();
        booka.setAuthorId(author.getId());
        bookDaoImpl.create(booka);

        Book bookb = TestDataUtil.createTestBookb();
        bookb.setAuthorId(author.getId());
        bookDaoImpl.create(bookb);

        Book bookc = TestDataUtil.createTestBookc();
        bookc.setAuthorId(author.getId());
        bookDaoImpl.create(bookc);

        List<Book> books = bookDaoImpl.findMany();

        Assertions.assertThat(books).size().isEqualTo(3);
        Assertions.assertThat(books)
                .hasSize(3)
                .containsExactly(booka, bookb, bookc);

    }

    @Test
    public void testThatBookCanBeUpdated(){

        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);

        Book booka = TestDataUtil.createTestBooka();
        booka.setAuthorId(author.getId());
        bookDaoImpl.create(booka);

        booka.setTitle("UPDATED");
        bookDaoImpl.update(booka.getIsbn(), booka);
        Optional<Book> result = bookDaoImpl.findOne(booka.getIsbn());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(booka);

    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorB();
        authorDao.create(author);
        Book book =  TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());
        bookDaoImpl.delete(book.getIsbn());

        Optional<Book> result = bookDaoImpl.findOne(book.getIsbn());
        Assertions.assertThat(result).isEmpty();
    }


}