package com.storefront.service;

import com.storefront.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    User findUserById(Long userId);

    int saveUser(User user);

    int updateUser(User user);
}
