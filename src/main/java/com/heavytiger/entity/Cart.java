package com.heavytiger.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 购物车模块
 */
public class Cart {

    private Map<Integer ,CartItem> items = new LinkedHashMap<>();

    public void addItem(CartItem cartItem){
        CartItem tempItem = items.get(cartItem.getId());
        if(tempItem == null) {
            items.put(cartItem.getId(), cartItem);
        } else {
            tempItem.setCount(tempItem.getCount() + 1);
            tempItem.setTotalPrice(tempItem.getPrice().multiply(new BigDecimal(tempItem.getCount())));
        }
    }

    public void deleteItem(Integer id) {
        items.remove(id);
    }

    public void clearItem() {
        items.clear();
    }

    public void updateCount(Integer id, Integer newCount) {
        CartItem tempItem = items.get(id);
        if(tempItem != null) {
            tempItem.setCount(newCount);
            tempItem.setTotalPrice(tempItem.getPrice().multiply(new BigDecimal(tempItem.getCount())));
        }
    }

    public Cart() {
    }

    public Cart(Map<Integer ,CartItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice =  totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer ,CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer ,CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
