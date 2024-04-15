package com.storefront.Controller;

import com.storefront.model.LandingPage;
import com.storefront.service.LandingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/index/")
public class LandingPageController {
    static int num = 0;
    @Autowired
    LandingServiceImpl landingService;
    Logger logger = LoggerFactory.getLogger( LandingPageController.class );

    @GetMapping()
    public List<LandingPage> viewAllProducts(@RequestParam("pageSize") int pageSize) {
        logger.info( "Request to fetch all products" );
        List<LandingPage> landingPageList = landingService.viewAllProducts( num ,pageSize);
        if (landingPageList.size() == pageSize) {
            num += 1;
        } else {
            num = 0;
        }
        return landingPageList;
    }

    @GetMapping("search/")
    public List<LandingPage> searchByKeyword(@RequestParam("keyword") String keyword) {
        logger.info( "Request to fetch all products starting with the keyword " + keyword );
        if(keyword.length()<3){
            return Collections.emptyList();
        }
        return landingService.searchByKeyword( keyword );
    }
}
