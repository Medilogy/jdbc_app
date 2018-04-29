package pl.sda.poznan.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtils {

    private static final String DB_URL_PROPERTY_NAME = "url";
    private DbUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Connection getConnection() {
        Properties properties = new Properties();
        try {
            properties.load(DbUtils.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            throw new RuntimeException(
                    "Cannot load db.properties file. Please create this file from db.properties.template");
        }
        try {
            return DriverManager
                    .getConnection(properties.getProperty(DB_URL_PROPERTY_NAME), properties);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to database" + e.getMessage());
        }
    }
}
