package com.storefront.service;

import com.storefront.dao.LandingPageDao;
import com.storefront.model.LandingPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LandingServiceImpl implements LandingService {

    @Autowired
    LandingPageDao landingPageDao;

    Logger logger = LoggerFactory.getLogger( LandingServiceImpl.class );

    @Override
    public List<LandingPage> viewAllProducts(int num, int pageSize) {
        logger.info( "Inside LandingServiceImpl.viewAllProducts method" );
        return landingPageDao.viewAllProducts( num ,pageSize);
    }

    @Override
    public List<LandingPage> searchByKeyword(String keyword) {
        logger.info( "Inside LandingServiceImpl.searchByKeyword method" );
        return landingPageDao.searchByKeyword( keyword );
    }
}
