package kucendro.db;

import java.sql.Connection;

public class connector {

    private static Connection conn;

    private static Connection connection() throws Exception {
        return java.sql.DriverManager.getConnection(
                System.getenv("DATABASE_URL"),
                System.getenv("DATABASE_USER"),
                System.getenv("DATABASE_PASSWORD"));
    }

    public static Connection getConnection() throws Exception {
        if (conn != null && !conn.isClosed()) {
            return conn;
        }
        conn = connection();
        return conn;
    }

    public static void closeConnection() throws Exception {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
