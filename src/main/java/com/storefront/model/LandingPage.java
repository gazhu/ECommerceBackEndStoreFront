package com.storefront.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LandingPage {

    Long productId;
    String productName;
    String vendorName;
    Float price;
}
