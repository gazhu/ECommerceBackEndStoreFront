package com.storefront.service;

import com.storefront.model.LandingPage;

import java.util.List;

public interface LandingService {
    List<LandingPage> viewAllProducts(int num, int pageSize);

    List<LandingPage> searchByKeyword(String keyword);

}
