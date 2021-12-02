package com.heavytiger.service.impl;

import com.heavytiger.dao.BookDao;
import com.heavytiger.dao.OrderDao;
import com.heavytiger.dao.OrderItemDao;
import com.heavytiger.dao.impl.BookDaoImpl;
import com.heavytiger.dao.impl.OrderDaoImpl;
import com.heavytiger.dao.impl.OrderItemDaoImpl;
import com.heavytiger.entity.*;
import com.heavytiger.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao = new OrderDaoImpl();
    private final OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 创建的订单号
        String orderId = System.currentTimeMillis()+""+userId;
        // 创建订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);
        // 遍历购物车中的商品项，转换成为订单项保存到数据库中
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        // 清空购物车
        cart.clearItem();
        return orderId;
    }
}