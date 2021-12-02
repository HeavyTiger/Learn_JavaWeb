package com.heavytiger.service;

import com.heavytiger.entity.Book;
import com.heavytiger.entity.Page;

import java.util.List;

public interface BookService {
    /**
     * 添加书籍
     * @param book 添加的书籍
     */
    public void addBook(Book book) ;

    /**
     * 通过id删除书籍
     * @param id 需要删除的用户名
     */
    public void deleteBookById(Integer id) ;

    /**
     * 更新书籍
     * @param book 更新书籍，通过id进行索引，记得加id
     */
    public void updateBook (Book book) ;

    /**
     * 根据id对书籍进行索引，得到书籍信息
     * @param id 搜索本id
     * @return 若查询成功返回Book类对象，否则返回null
     */
    public Book queryBookById(Integer id) ;

    /**
     * 返回全部查询到的书籍
     * @return 返回全部查询到的书籍
     */
    public List<Book> queryBooks() ;


    /**
     * 通过当前的页码和每页的书籍总数获得Page分页
     * @param pageNo 当前的页码
     * @param pageSize  每页的书籍总数
     * @return 返回Page即分页类型
     */
    public Page<Book> pageBooks(Integer pageNo, Integer pageSize);


    /**
     * 通过价格区间获得页面数据
     * @param pageNo 当前页面
     * @param pageSize 页面一页的内容
     * @param min 最小金额
     * @param max 最大金额
     * @return 页面信息
     */
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
