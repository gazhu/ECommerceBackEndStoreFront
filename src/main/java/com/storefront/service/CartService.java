package com.storefront.service;

import com.storefront.model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> viewCart(Long userId);

    int saveCart(Cart cart);

    Cart findCartByOrderNo(Long orderNo);
}
