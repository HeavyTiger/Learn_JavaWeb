package com.heavytiger.service.impl;

import com.heavytiger.dao.BookDao;
import com.heavytiger.dao.impl.BookDaoImpl;
import com.heavytiger.entity.Book;
import com.heavytiger.entity.Page;
import com.heavytiger.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();

    /**
     * 添加书籍
     *
     * @param book 添加的书籍
     */
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    /**
     * 通过id删除书籍
     *
     * @param id 需要删除的用户名
     */
    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBook(id);
    }

    /**
     * 更新书籍
     *
     * @param book 更新书籍，通过id进行索引，记得加id
     */
    @Override
    public void updateBook(Book book) {

        bookDao.updateBook(book);
    }

    /**
     * 根据id对书籍进行索引，得到书籍信息
     *
     * @param id 搜索本id
     * @return 若查询成功返回Book类对象，否则返回null
     */
    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    /**
     * 返回全部查询到的书籍
     *
     * @return 返回全部查询到的书籍
     */
    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    /**
     * @param pageNo   当前的页码
     * @param pageSize 每页的书籍总数
     * @return 若查询异常，即pageNo小于等于0，pageSize小于1或大于50，返回null
     */
    @Override
    public Page<Book> pageBooks(Integer pageNo, Integer pageSize) {
        Page<Book> pageBooks = new Page<>();
        pageBooks.setPageNo(pageNo);
        pageBooks.setPageSize(pageSize);
        pageBooks.setPageTotalCount(bookDao.queryForPageTotalCount());
        int pageTotal = pageBooks.getPageTotalCount() / pageSize;
        pageBooks.setPageTotal((pageBooks.getPageTotalCount() % pageSize > 0) ? pageTotal + 1 : pageTotal);
        if (pageNo < 1) {
            pageBooks.setPageNo(1);
        } else if (pageNo > pageBooks.getPageTotal()) {
            pageBooks.setPageNo(pageBooks.getPageTotal());
        } else if (pageSize < 1) {
            pageBooks.setPageSize(1);
        } else if (pageSize > 50) {
            pageBooks.setPageSize(50);
        }
        pageBooks.setItems(bookDao.queryForItems(pageBooks.getPageNo(), pageBooks.getPageSize()));
        return pageBooks;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> pageBooks = new Page<>();
        pageBooks.setPageNo(pageNo);
        pageBooks.setPageSize(pageSize);
        pageBooks.setPageTotalCount(bookDao.queryForPageTotalCountByPrice(min, max));
        int pageTotal = pageBooks.getPageTotalCount() / pageSize;
        pageBooks.setPageTotal((pageBooks.getPageTotalCount() % pageSize > 0) ? pageTotal + 1 : pageTotal);
        if (pageNo < 1) {
            pageBooks.setPageNo(1);
        } else if (pageNo > pageBooks.getPageTotal()) {
            pageBooks.setPageNo(pageBooks.getPageTotal());
        } else if (pageSize < 1) {
            pageBooks.setPageSize(1);
        } else if (pageSize > 50) {
            pageBooks.setPageSize(50);
        }
        pageBooks.setItems(bookDao.queryForItemsByPrice(pageBooks.getPageNo(), pageBooks.getPageSize(), min, max));
        return pageBooks;
    }
}