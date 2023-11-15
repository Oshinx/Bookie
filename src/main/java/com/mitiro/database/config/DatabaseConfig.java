package com.mitiro.database.config;


import com.mitiro.database.dao.impl.BookDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource){
       return new JdbcTemplate(dataSource);
    }

    @Bean
    public BookDaoImpl.BookRowMapper  bookRowMapper(){
        return new BookDaoImpl.BookRowMapper();
    }

}
