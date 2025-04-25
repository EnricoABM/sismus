package com.duviri.sismus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {
        String USER = "sa";
        String PASS = "";
        String URL = "jdbc:h2:mem:sismus";

        return DriverManager.getConnection(URL, USER, PASS);
    }
}
