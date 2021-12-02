package com.heavytiger.dao.impl;

import com.heavytiger.dao.BookDao;
import com.heavytiger.entity.Book;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
class BookDaoImplTest {

    BookDao bookDao = new BookDaoImpl();

    @Test
    void addBook() {
        Book testBook = new Book(null, "Hello World", "dcm", new BigDecimal("1024.66"), 99, 101, null);
        bookDao.addBook(testBook);
    }

    @Test
    void deleteBook() {
        bookDao.deleteBook(21);
    }

    @Test
    void updateBook() {
        Book testBook = new Book(21, "Hello World", "dcmnb", new BigDecimal("1024.66"), 99, 101, null);
        bookDao.updateBook(testBook);
    }

    @Test
    void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    void queryForItems() {
        for (Book book : bookDao.queryForItems(1, 4)) {
            System.out.println(book);
        }
    }
}