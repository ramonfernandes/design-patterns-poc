package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    final private static String driver = "com.mysql.cj.jdbc.Driver";
    final private static String url = "jdbc:mysql://localhost/library";
    final private static String user = "teste";
    final private static String password = "teste";

    public static Connection abrir() {
        try {
            Class.forName(driver);
            return (Connection) DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}