package com.duviri.sismus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {
        String USER = "CHANGEME";
        String PASS = "CHANGEME";
        String URL = "jdbc:mysql://localhost:3306/sismus";

        return DriverManager.getConnection(URL, USER, PASS);
    }
}
