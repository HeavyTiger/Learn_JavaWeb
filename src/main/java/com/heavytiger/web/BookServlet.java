package com.heavytiger.web;

import com.heavytiger.entity.Book;
import com.heavytiger.entity.Page;
import com.heavytiger.service.BookService;
import com.heavytiger.service.impl.BookServiceImpl;
import com.heavytiger.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    private final BookService bookService = new BookServiceImpl();

    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.addBook(book);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0) + 1;
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=pageBooks&pageNo=" + pageNo);
    }

    protected void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        bookService.deleteBookById(Integer.valueOf(id));
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=pageBooks&pageNo=" + req.getParameter("pageNo"));
    }

    protected void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=pageBooks&pageNo=" + req.getParameter("pageNo"));
    }

    protected void queryOneBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Book book = bookService.queryBookById(id);
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void queryBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void pageBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE);
        Page<Book> pageBooks = bookService.pageBooks(pageNo, pageSize);
        pageBooks.setUrl("manager/bookServlet?action=pageBooks");
        req.setAttribute("pageBooks", pageBooks);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
