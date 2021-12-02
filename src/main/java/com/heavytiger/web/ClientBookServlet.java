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

public class ClientBookServlet extends BaseServlet {

    private final BookService bookService = new BookServiceImpl();

    protected void pageBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 4);
        Page<Book> pageBooks = bookService.pageBooks(pageNo, pageSize);
        pageBooks.setUrl("client/bookServlet?action=pageBooks");
        req.setAttribute("pageBooks", pageBooks);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 4);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        Page<Book> pageBooks = bookService.pageByPrice(pageNo, pageSize, min, max);
        pageBooks.setUrl("client/bookServlet?action=pageByPrice" + "&min=" + min + "&max=" + max);
        req.setAttribute("pageBooks", pageBooks);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
