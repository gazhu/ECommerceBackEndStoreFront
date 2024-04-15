package com.storefront.service;

import com.storefront.constants.Constants;
import com.storefront.model.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    Logger logger= LoggerFactory.getLogger( KafkaProducerService.class );
    @Autowired
    KafkaTemplate<String,Object> template;

    public void sendCart(Cart cart){
        template.send( Constants.topicName, cart );
        logger.info( "Cart sent to OMS"+cart.toString() );
    }

}
