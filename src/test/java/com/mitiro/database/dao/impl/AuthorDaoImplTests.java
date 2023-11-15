package com.mitiro.database.dao.impl;


import com.mitiro.database.TestDataUtil;
import com.mitiro.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import  org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private  AuthorDaoImpl underTest;


    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Author author = TestDataUtil.createTestAuthor();

        underTest.create(author);

        verify(jdbcTemplate).update( eq("INSERT INTO authors (id, name, age) VALUES (?,?,?)"),
                eq(1L), eq("Abigail Rose"), eq(80)
                );
    }

    @Test
    public void testThatFindOneGenerateTheCorrectSql(){
      underTest.findOne(1L);

      verify(jdbcTemplate).query(
              eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1 "),
              ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper> any(), eq(1L)
      );
    }

    @Test
    public void testThatFindManyGenerateCorrectSql(){
        underTest.findMany();

        verify(jdbcTemplate).query(  eq("SELECT id, name, age FROM authors  "),
                                 ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any());
    }


    @Test
    public void testThatUpdateGeneratesCorrectSql(){
        Author author = TestDataUtil.createTestAuthor();
        underTest.update(author.getId(), author);

        verify(jdbcTemplate).update(
                "UPDATE authors SET id=?, name= ?, age = ? WHERE id = ?",
                1L, "Abigail Rose", 80, 1L
        );
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql(){
        Author author = TestDataUtil.createTestAuthor();
        underTest.delete(author.getId());

        verify(jdbcTemplate).update("DELETE FROM authors WHERE id = ?", author.getId());
    }

}
