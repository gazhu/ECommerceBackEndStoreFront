package com.storefront.service;

import com.storefront.config.WebClientConfig;
import com.storefront.constants.Constants;
import com.storefront.dao.CartDao;
import com.storefront.exceptions.ProductDoesntExistException;
import com.storefront.model.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartServiceImpl implements CartService {
    @Autowired
    CartDao cartDao;
    Logger logger = LoggerFactory.getLogger( CartServiceImpl.class );
    @Autowired
    WebClientConfig webClientConfig;



    @Override
    public List<Cart> viewCart(Long userId) {
        logger.info( "Inside CartServiceImpl.viewCart method" );
        return cartDao.viewCart( userId );
    }

    @Override
    public int saveCart(Cart cart) {
        logger.info( "Inside CartServiceImpl.saveCart method" );
        Boolean doesExist=webClientConfig.webClientConfigObject()
                .get()
                .uri( Constants.productFindByIdUri
                                + cart.getProductId().toString()
                         )
                .retrieve().bodyToMono(Boolean.class).block();
        if (!doesExist) {
            logger.error( "Product doesn't exist" );
            throw new ProductDoesntExistException( "Product "+ cart.getProductName()+" does not exist." );
        }
        return cartDao.cartSave( cart );
    }

    @Override
    public Cart findCartByOrderNo(Long orderNo) {
        logger.info( "Inside CartServiceImpl.findCartByOrderNo method" );
        return cartDao.findCartByOrderNo( orderNo );
    }
}
