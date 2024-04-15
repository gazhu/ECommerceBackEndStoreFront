package com.storefront.dao;

import com.storefront.constants.Constants;
import com.storefront.model.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartDao {
    Logger logger = LoggerFactory.getLogger( CartDao.class );

    public List<Cart> viewCart(Long userId) {
        logger.info( "Inside CartDao.viewCart method" );
        List<Cart> carts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    DbConnection.getObject().getConnection()
                            .prepareStatement( Constants.selectAllProductsForUserQuery
                                    + "where c.user_id=?" );
            preparedStatement.setLong( 1, userId );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cart cart = new Cart();
                cart.setOrderDate( String.valueOf( resultSet.getTimestamp( "order_date" ) ) );
                cart.setStatus( resultSet.getString( "status" ) );
                cart.setPrice( resultSet.getFloat( "price" ) );
                cart.setProductId( resultSet.getLong( "product_id" ) );
                cart.setVendorName( resultSet.getString( "vendor_name" ) );
                cart.setProductName( resultSet.getString( "product_name" ) );
                cart.setUserId( resultSet.getLong( "user_id" ) );
                cart.setUserName( resultSet.getString( "user_name" ) );
                cart.setOrderNo( resultSet.getLong( "orderNo" ) );
                carts.add( cart );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return carts;
    }

    public int cartSave(Cart cart) {
        logger.info( "Inside CartDao.cartSave method" );
        int res = 0;
        Long productId = cart.getProductId();
        List<Cart> carts = viewCart( cart.getUserId() );
        for (Cart cart1 : carts) {
            if (cart1.getProductId().equals( productId )) {
                return -1;
            }
        }
        try {
            PreparedStatement preparedStatement =
                    DbConnection.getObject()
                            .getConnection()
                            .prepareStatement( Constants.insertCartQuery );
            preparedStatement.setLong( 1, cart.getProductId() );
            preparedStatement.setLong( 2, cart.getUserId() );
            preparedStatement.setString( 3, cart.getStatus() );
            res = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public Cart findCartByOrderNo(Long orderNo) {
        logger.info( "Inside CartDao.findCartByOrderNo method" );
        Cart cart = new Cart();
        try {
            PreparedStatement preparedStatement =
                    DbConnection.getObject().getConnection()
                            .prepareStatement( Constants.selectAllProductsForUserQuery
                                    + "c.orderNo=?" );
            preparedStatement.setLong( 1, orderNo );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cart.setOrderDate( String.valueOf( resultSet.getTimestamp( "order_date" ) ) );
                cart.setStatus( resultSet.getString( "status" ) );
                cart.setPrice( resultSet.getFloat( "price" ) );
                cart.setProductId( resultSet.getLong( "product_id" ) );
                cart.setVendorName( resultSet.getString( "vendor_name" ) );
                cart.setProductName( resultSet.getString( "product_name" ) );
                cart.setUserId( resultSet.getLong( "user_id" ) );
                cart.setUserName( resultSet.getString( "user_name" ) );
                cart.setOrderNo( resultSet.getLong( "orderNo" ) );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cart;
    }
}
