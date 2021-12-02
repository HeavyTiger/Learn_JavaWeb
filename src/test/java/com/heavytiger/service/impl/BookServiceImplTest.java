package com.heavytiger.service.impl;

import com.heavytiger.entity.Book;
import com.heavytiger.service.BookService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

    BookService bookService = new BookServiceImpl();

    @Test
    void addBook() {
        Book testBook = new Book(null, "Hello World", "dcm", new BigDecimal("1024.66"), 99, 101, null);
        bookService.addBook(testBook);
    }

    @Test
    void addBooks() {
        for(int i = 1; i < 30; i++) {
            Book testBook = new Book(null, "Hello World" + i, "dcm" + i, new BigDecimal("1024.66"), 99, 101, null);
            bookService.addBook(testBook);
        }
    }

    @Test
    void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    void updateBook() {
        Book testBook = new Book(22, "Hello World", "dcm nb", new BigDecimal("1024.66"), 99, 101, null);
        bookService.updateBook(testBook);
    }

    @Test
    void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    void pageBooks() {

        assertNull(bookService.pageBooks(-1, 10));
        assertNull(bookService.pageBooks(1, 60));
        assertNull(bookService.pageBooks(1, 3));
        System.out.println(bookService.pageBooks(1, 10));
    }
}