package com.storefront.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final DbConnection db = new DbConnection();

    private DbConnection() {
    }

    public static DbConnection getObject() {
        return db;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/ecommerce", "root", "1234567890" );
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}
