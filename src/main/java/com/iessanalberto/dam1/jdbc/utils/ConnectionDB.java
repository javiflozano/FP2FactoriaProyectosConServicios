package com.iessanalberto.dam1.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    private final static String url = "jdbc:mysql://54.37.220.4:3306/pruebasFP2?serverTimezone=UTC";
    private final static String username = "fpuser";
    private final static String password = "FPuserp@ssw0rd";
    private static Connection connection = null;
    public static Connection connect() throws Exception{
        if (connection == null){
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (Exception exception){
                throw new Exception("Error de conexión con la base de datos.\n Información del error: " + exception.getMessage());
            }
        }
        return connection;
    }
}
