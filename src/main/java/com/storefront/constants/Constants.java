package com.storefront.constants;

public class Constants {
    public static String selectAllProductsForUserQuery = "SELECT \n" +
            "    c.orderNo,\n" +
            "    c.product_id,\n" +
            "    c.user_id,\n" +
            "    c.order_date,\n" +
            "    c.status,\n" +
            "    p.product_name,\n" +
            "    v.vendor_name,\n" +
            "    p.price,\n" +
            "    u.user_name\n" +
            "FROM\n" +
            "    cart c\n" +
            "        LEFT JOIN\n" +
            "    products p ON p.product_id = c.product_id\n" +
            "        LEFT JOIN\n" +
            "    user u ON u.user_id = c.user_id\n" +
            "        LEFT JOIN\n" +
            "    vendor v ON p.vendor_id = v.vendor_id ";
    public static String insertCartQuery = "insert into cart(product_id,user_id,order_date,status) values(?,?,current_timestamp,?);";
    public static String selectAllProductsQuery =
            "select p.product_id, p.product_name, v.vendor_name, p.price from products p " +
                    "left join vendor v on p.vendor_id=v.vendor_id ";
    public static String selectAllUserQuery = "select * from user ";
    public static String addUserQuery = "insert into user(user_name) values(?)";
    public static String updateUserQuery = "update user set user_name=? where user_id=?";

    public static String groupName="StoreFront";
    public static String topicName="CartSend";

    public static String productFindByIdUri="http://localhost:8080/products/storefront/";
}
