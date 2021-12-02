package com.heavytiger.dao.impl;

import com.heavytiger.dao.BaseDao;
import com.heavytiger.dao.OrderItemDao;
import com.heavytiger.entity.OrderItem;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO t_order_item(`name`, `count`, `price`, `total_price`, `order_id`) VALUES(?, ?, ?, ?, ?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }
}
