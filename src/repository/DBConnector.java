package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnector {

    public static Connection connection;

    private static Connection connect() {
        String dbDriver = Properties.DB_DRIVER;
        String dbBaseUrl = Properties.DB_BASE_URL;
        String dbName = Properties.DB_NAME;
        String url = dbDriver + dbBaseUrl + dbName;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static Connection getConnection() {
        if (connection == null) {
            connection = connect();
        }
        return connection;
    }
}
