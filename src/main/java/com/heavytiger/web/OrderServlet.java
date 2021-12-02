package com.heavytiger.web;

import com.heavytiger.entity.Cart;
import com.heavytiger.entity.User;
import com.heavytiger.service.OrderService;
import com.heavytiger.service.impl.OrderServiceImpl;
import com.heavytiger.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class OrderServlet extends BaseServlet {

    private final OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 调用orderService.createOrder(Cart, userId);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取userId
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = loginUser.getId();
        String orderId = null;

        orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId", orderId);
        // 请求转发
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
