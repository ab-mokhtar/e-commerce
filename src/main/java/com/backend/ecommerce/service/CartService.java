package com.backend.ecommerce.service;



import com.backend.ecommerce.entity.Cart;
import com.backend.ecommerce.entity.ProductInOrder;
import com.backend.ecommerce.entity.User;

import java.util.Collection;


public interface CartService {
    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}
