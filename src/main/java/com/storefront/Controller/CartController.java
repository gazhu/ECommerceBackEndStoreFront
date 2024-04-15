package com.storefront.Controller;

import com.storefront.model.Cart;
import com.storefront.service.CartServiceImpl;
import com.storefront.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cart/")
@RestController
public class CartController {
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    KafkaProducerService kafkaProducerService;
    Logger logger = LoggerFactory.getLogger( CartController.class );

    @GetMapping("{userId}")
    public List<Cart> viewCart(@PathVariable("userId") Long userId) {
        logger.info( "Request to view cart for userId " + userId );
        return cartService.viewCart( userId );
    }

    @PostMapping("save")
    public ResponseEntity<String> saveCart(@RequestBody Cart cart) {
        logger.info( "Request to save Product to cart" );
        Long orderNo = cart.getOrderNo();
        if (orderNo != null && cartService.findCartByOrderNo( orderNo ).getOrderNo() != null) {
            logger.error( "Invalid OrderNo" );
            return new ResponseEntity<>( "Invalid OrderNo", HttpStatus.BAD_REQUEST );
        } else {
            int res = cartService.saveCart( cart );
            if (res > 0) {
                logger.trace( "Product saved to Cart" );

                kafkaProducerService.sendCart( cart );
                return new ResponseEntity<>( "Product saved to cart", HttpStatus.OK );
            } else if (res==Integer.MIN_VALUE) {
                logger.error( "Product doesn't exist" );
                return new ResponseEntity<>( "Product doesn't exist, couldn't be added",HttpStatus.BAD_REQUEST );
            } else {
                logger.error( "Product couldn't be added to cart" );
                return new ResponseEntity<>( "Product can't be added", HttpStatus.BAD_REQUEST );
            }
        }
    }
}
