package com.mitiro.database;

import com.mitiro.database.domain.Author;
import com.mitiro.database.domain.Book;

public final  class TestDataUtil {

    private TestDataUtil(){}

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .age(80)
                .name("Abigail Rose")
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .age(44)
                .name("Thomas Cronin")
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .age(30)
                .name("Jesse A Cassey")
                .build();
    }

    public static Author createTestAuthorD() {
        return Author.builder()
                .id(4L)
                .age(60)
                .name("James Bond")
                .build();
    }

    public static Book createTestBook() {
        return  Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
    }

    public static Book createTestBooka() {
        return Book.builder()
                .isbn("978-1-2345-6789-1")
                .title("Beyond the Horizon")
                .authorId(1L)
                .build();
    }
    public static Book createTestBookb() {
        return Book.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Last Ember")
                .authorId(1L)
                .build();
    }
    public static Book createTestBookc() {
        return Book.builder()
                .isbn("445-1-5552-6789-0")
                .title("The in the Attic")
                .authorId(1L)
                .build();
    }
}
