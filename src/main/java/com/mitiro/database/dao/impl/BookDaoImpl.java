package com.mitiro.database.dao.impl;

import com.mitiro.database.dao.BookDao;
import com.mitiro.database.domain.Author;
import com.mitiro.database.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    private  final BookRowMapper bookRowMapper;

    public BookDaoImpl(final  JdbcTemplate jdbcTemplate,BookRowMapper bookRowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.bookRowMapper = bookRowMapper;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books(isbn, title, author_id) VALUES (?,?,?)",
                book.getIsbn(), book.getTitle(), book.getAuthorId()
        );

    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> results =   jdbcTemplate.query(  "SELECT isbn, title, author_id FROM books WHERE isbn = ?  ",
                bookRowMapper, isbn);
        return results.stream().findFirst();
    }

    @Override
    public List<Book> findMany() {
        return jdbcTemplate.query("SELECT isbn, title, author_id from books", bookRowMapper);
    }

    @Override
    public void update(String isbn, Book book) {
        jdbcTemplate.update("UPDATE books SET isbn = ? , title = ? , author_id= ?  WHERE isbn = ?",
                              book.getIsbn(), book.getTitle(), book.getAuthorId(), isbn
                             );
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update("DELETE FROM books WHERE isbn = ?", isbn);
    }

    public static  class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

            return Book.builder()
                            .isbn(rs.getString("isbn"))
                            .title(rs.getString("title"))
                            .authorId(rs.getLong("author_id"))
                            .build();
        }
    }
}
