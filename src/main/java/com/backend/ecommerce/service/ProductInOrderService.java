package com.backend.ecommerce.service;


import com.backend.ecommerce.entity.ProductInOrder;
import com.backend.ecommerce.entity.User;


public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);
    ProductInOrder findOne(String itemId, User user);
}
