package com.heavytiger.dao;

import com.heavytiger.entity.Book;

import java.util.List;

public interface BookDao {
    public int addBook(Book book);

    public int deleteBook(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();

    public List<Book> queryForItems(Integer pageNo, Integer pageSize);

    public Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForItemsByPrice(Integer pageNo, Integer pageSize, int min, int max);
}
