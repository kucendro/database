package kucendro.db;

import java.sql.Connection;

public class connector implements kucendro.global.interfaces.connector {

    private static Connection connection() throws Exception {
        return java.sql.DriverManager.getConnection(
                System.getenv("DATABASE_URL"),
                System.getenv("DATABASE_USER"),
                System.getenv("DATABASE_PASSWORD"));
    }

    public static Connection getConnection() throws Exception {

        try {
            if (connection() != null && !connection().isClosed()) {
                return connection();
            }
            return connection();
        } catch (Exception e) {
            logger.log("Error obtaining database connection", e);
            throw e;
        }
    }

    public static void closeConnection() throws Exception {

        try {
            if (connection() != null && !connection().isClosed()) {
                connection().close();
            }
        } catch (Exception e) {
            logger.log("Error closing database connection", e);
            throw e;
        }
    }

    public static boolean pingDatabase() throws Exception {
        try {
            Connection conn = getConnection();
            return conn != null && !conn.isClosed();
        } catch (Exception e) {
            // ! Do not log this... the expected behavior
            return false;
        }
    }
}
