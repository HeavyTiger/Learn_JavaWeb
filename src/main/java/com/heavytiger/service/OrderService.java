package com.heavytiger.service;

import com.heavytiger.entity.Cart;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);

}
