package com.storefront.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cart extends LandingPage {
    Long orderNo;
    Long userId;
    String userName;
    String orderDate;
    String status;
}
