package com.storefront.service;

import com.storefront.dao.UserDao;
import com.storefront.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    Logger logger = LoggerFactory.getLogger( UserServiceImpl.class );

    @Override
    public List<User> findAllUsers() {
        logger.info( "Inside UserServiceImpl.findAllUsers method" );
        List<User> userList = userDao.findAllUser();
        return (userList.size() != 0 ? userList : Collections.emptyList());
    }

    @Override
    public User findUserById(Long userId) {
        logger.info( "Inside UserServiceImpl.findUserById method" );
        return userDao.findUserById( userId );
    }

    @Override
    public int saveUser(User user) {
        logger.info( "Inside UserServiceImpl.saveUser method" );
        return userDao.saveUser( user );
    }

    @Override
    public int updateUser(User user) {
        logger.info( "Inside UserServiceImpl.updateUser method" );
        return userDao.updateUser( user );
    }
}
