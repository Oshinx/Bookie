package com.mitiro.database.dao.impl;


import com.mitiro.database.TestDataUtil;
import com.mitiro.database.domain.Author;
import com.mitiro.database.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@ExtendWith(MockitoExtension.class)

public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    BookDaoImpl bookDaoImpl;

    @Test
    public void testThatCreateBookGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBook();


        bookDaoImpl.create(book);

        Mockito.verify(jdbcTemplate).update(
                eq("INSERT INTO books(isbn, title, author_id) VALUES (?,?,?)"),
                eq("978-1-2345-6789-0"), eq("The shadow in the Attic"), eq(1L)
        );

    }

    @Test
    public void testThatFindOneBookGenerateTheCorrectSql(){
        bookDaoImpl.findOne("978-1-2345-6789-0");


        verify(jdbcTemplate).query(
                eq("SELECT isbn, title author_id FROM books WHERE isbn = ?  "),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper> any(), eq("978-1-2345-6789-0")
        );
    }

    @Test
    public void testThatFindManyGenerateCorrectSql() {
        bookDaoImpl.findMany();

        verify(jdbcTemplate).query(eq("SELECT isbn, title, author_id from books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any());
    }


    @Test
    public void testThatUpdateGeneratesCorrectSql(){
        Author author = TestDataUtil.createTestAuthorB();
        Book book = TestDataUtil.createTestBook();
        book.setTitle("UPDATED");
        bookDaoImpl.update(book.getIsbn(), book);

        verify(jdbcTemplate).update(
                "UPDATE books SET isbn = ? , title = ? , author_id= ?  WHERE isbn = ?",
                1L, "Abigail Rose", 80, 1L
        );

    }

    @Test
    public  void testThatDeleteGeneratesTheCorrectSql(){
        Book book = TestDataUtil.createTestBook();
        bookDaoImpl.delete(book.getIsbn());
        verify(jdbcTemplate).update("DELETE FROM books WHERE isbn = ?", book.getIsbn());
    }



}
