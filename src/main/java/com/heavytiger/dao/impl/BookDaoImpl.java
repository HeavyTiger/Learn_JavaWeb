package com.heavytiger.dao.impl;

import com.heavytiger.dao.BaseDao;
import com.heavytiger.dao.BookDao;
import com.heavytiger.entity.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book){
        String sql = "INSERT INTO t_book(name, author, price, sales, stock, img_path) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBook(Integer id) {
        String sql = "DELETE FROM t_book WHERE id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "UPDATE t_book SET name = ?, author = ?, price = ?, sales = ?, stock = ?, img_path = ? WHERE id = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(),
                book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "SELECT id, name, author, price, sales, stock, img_path as imgPath FROM t_book WHERE id = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "SELECT id, name, author, price, sales, stock, img_path as imgPath FROM t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "SELECT COUNT(*) AS count FROM t_book";
        Number count =  queryForSingleValue(Number.class, sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItems(Integer pageNo, Integer pageSize) {
        int begin = (pageNo - 1) * pageSize;
        String sql = "SELECT id, name, author, price, sales, stock, img_path as imgPath FROM t_book Limit ?, ?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "SELECT COUNT(*) AS count FROM t_book WHERE price BETWEEN ? AND ?";
        Number count =  queryForSingleValue(Number.class, sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItemsByPrice(Integer pageNo, Integer pageSize, int min, int max) {
        int begin = (pageNo - 1) * pageSize;
        String sql = "SELECT id, name, author, price, sales, stock, img_path as imgPath FROM t_book WHERE price BETWEEN ? AND ? Limit ?, ?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }

}
