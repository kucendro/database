package kucendro.db;

import java.sql.Connection;

public class connector {

    private static Connection connection() throws Exception {
        return java.sql.DriverManager.getConnection(
                System.getenv("DATABASE_URL"),
                System.getenv("DATABASE_USER"),
                System.getenv("DATABASE_PASSWORD"));
    }

    public static Connection getConnection() throws Exception {
        if (connection() != null && !connection().isClosed()) {
            return connection();
        }
        return connection();
    }

    public static void closeConnection() throws Exception {
        if (connection() != null && !connection().isClosed()) {
            connection().close();
        }
    }

    public static boolean pingDatabase() throws Exception {
        try {
            Connection conn = getConnection();
            return conn != null && !conn.isClosed();
        } catch (Exception e) {
            return false;
        }
    }
}
