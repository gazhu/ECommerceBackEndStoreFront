package com.storefront.dao;

import com.storefront.constants.Constants;
import com.storefront.model.LandingPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LandingPageDao {
    Logger logger = LoggerFactory.getLogger( LandingPageDao.class );

    public List<LandingPage> viewAllProducts(int num, int pageSize) {
        logger.info( "Inside LandingPageDao.viewAllProducts method" );
        List<LandingPage> landingPageList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    DbConnection.getObject().getConnection().prepareStatement( Constants.selectAllProductsQuery
                            + "limit ?,?" );
            preparedStatement.setInt( 1, pageSize * num );
            preparedStatement.setInt( 2, pageSize);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LandingPage landingPage = new LandingPage();
                landingPage.setProductId( resultSet.getLong( "product_id" ) );
                landingPage.setVendorName( resultSet.getString( "vendor_name" ) );
                landingPage.setProductName( resultSet.getString( "product_name" ) );
                landingPage.setPrice( resultSet.getFloat( "price" ) );
                landingPageList.add( landingPage );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return landingPageList;
    }

    public List<LandingPage> searchByKeyword(String keyword) {
        logger.info( "Inside LandingPageDao.searchByKeyword method" );
        List<LandingPage> landingPageList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DbConnection.getObject().getConnection()
                    .prepareStatement( Constants.selectAllProductsQuery
                            + " where p.product_name like \'" + keyword + "%\'" );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LandingPage landingPage = new LandingPage();
                landingPage.setProductId( resultSet.getLong( "product_id" ) );
                landingPage.setVendorName( resultSet.getString( "vendor_name" ) );
                landingPage.setProductName( resultSet.getString( "product_name" ) );
                landingPage.setPrice( resultSet.getFloat( "price" ) );
                landingPageList.add( landingPage );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return landingPageList;
    }
}
