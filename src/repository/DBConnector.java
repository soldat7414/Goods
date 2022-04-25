package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnector {

    public static Connection connect() {
        String dbDriver = Properties.DB_DRIVER;
        String dbBaseUrl = Properties.DB_BASE_URL;
        String dbName = Properties.DB_NAME;
        String url = dbDriver + dbBaseUrl + dbName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
