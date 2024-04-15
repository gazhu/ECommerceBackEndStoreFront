package com.storefront.dao;

import com.storefront.constants.Constants;
import com.storefront.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {

    Logger logger = LoggerFactory.getLogger( UserDao.class );

    public int saveUser(User user) {
        logger.info( "Inside UserDao.saveUser method" );
        int res = 0;
        try {
            PreparedStatement preparedStatement =
                    DbConnection.getObject()
                            .getConnection()
                            .prepareStatement( Constants.addUserQuery );
            preparedStatement.setString( 1, user.getUserName() );
            res = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public int updateUser(User user) {
        logger.info( "Inside UserDao.updateUser method" );
        int res = 0;
        try {
            PreparedStatement preparedStatement =
                    DbConnection.getObject()
                            .getConnection()
                            .prepareStatement( Constants.updateUserQuery );
            preparedStatement.setString( 1, user.getUserName() );
            preparedStatement.setLong( 2, user.getUserId() );
            res = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public List<User> findAllUser() {
        logger.info( "Inside UserDao.findAllUser method" );
        List<User> userList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DbConnection.getObject()
                    .getConnection()
                    .prepareStatement( Constants.selectAllUserQuery );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId( resultSet.getLong( "user_id" ) );
                user.setUserName( resultSet.getString( "user_name" ) );
                userList.add( user );
            }
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }

        return userList;
    }

    public User findUserById(Long userId) {
        logger.info( "Inside UserDao.findUserById method" );
        User user = new User();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DbConnection.getObject()
                    .getConnection()
                    .prepareStatement( Constants.selectAllUserQuery
                            + " where user_id=?" );
            preparedStatement.setLong( 1, userId );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setUserId( resultSet.getLong( "user_id" ) );
                user.setUserName( resultSet.getString( "user_name" ) );
            }
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
        return user;
    }

}
