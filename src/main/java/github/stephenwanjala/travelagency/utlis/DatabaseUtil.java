package github.stephenwanjala.travelagency.utlis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/travel_agency";

    public static Connection getConnection() throws SQLException {
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        if (username == null || password == null) {
            throw new IllegalStateException("Database credentials not found in environment variables");
        }

        return DriverManager.getConnection(JDBC_URL, username, password);
    }
}
