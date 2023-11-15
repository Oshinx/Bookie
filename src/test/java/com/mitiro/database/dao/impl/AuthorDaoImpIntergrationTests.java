package com.mitiro.database.dao.impl;


import com.mitiro.database.TestDataUtil;
import com.mitiro.database.domain.Author;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
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
public class AuthorDaoImpIntergrationTests {

    private  AuthorDaoImpl underTest;


    @Autowired
    public AuthorDaoImpIntergrationTests(AuthorDaoImpl underTest){
        this.underTest =  underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedandRecalled(){
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);
        Author authorb = TestDataUtil.createTestAuthorB();
        underTest.create(authorb);
        Author authorc = TestDataUtil.createTestAuthorC();
        underTest.create(authorc);

        List<Author> authors = underTest.findMany();

        Assertions.assertThat(authors).size().isEqualTo(3);
        Assertions.assertThat(authors).hasSize(3)
                .containsExactly(author, authorb, authorc);

    }

    @Test
    public void testThatAuthorCanBeUpdated(){

        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);
        author.setName("UPDATED");
        underTest.update(author.getId(), author);
        Optional<Author> result = underTest.findOne(author.getId());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(author);

    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);

        underTest.delete(author.getId());

        Optional<Author> result = underTest.findOne(author.getId());
        Assertions.assertThat(result).isEmpty();

    }
}
